package guru.qa.niffler.controller;

import guru.qa.niffler.config.Callbacks;
import guru.qa.niffler.model.RegistrationModel;
import guru.qa.niffler.service.OauthSessionValidator;
import guru.qa.niffler.service.UserService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

  private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

  private static final String REGISTRATION_VIEW_NAME = "register";
  private static final String MODEL_USERNAME_ATTR = "username";
  private static final String MODEL_REG_FORM_ATTR = "registrationModel";
  private static final String MODEL_FRONT_URI_ATTR = "frontUri";
  private static final String MODEL_FINAL_STEP_BTN_TEXT_ATTR = "finalStep";
  private static final String REG_MODEL_ERROR_BEAN_NAME = "org.springframework.validation.BindingResult.registrationModel";

  private final UserService userService;
  private final OauthSessionValidator sessionValidator;
  private final String nifflerFrontUri;
  private final String mobileCustomScheme;
  private final String androidAppUri;

  @Autowired
  public RegisterController(UserService userService,
                            OauthSessionValidator sessionValidator,
                            @Value("${niffler-front.base-uri}") String nifflerFrontUri,
                            @Value("${oauth2.mobile-custom-scheme}") String mobileCustomScheme,
                            @Value("${oauth2.android-app-uri}") String androidAppUri) {
    this.userService = userService;
    this.sessionValidator = sessionValidator;
    this.nifflerFrontUri = nifflerFrontUri;
    this.mobileCustomScheme = mobileCustomScheme;
    this.androidAppUri = androidAppUri;
  }

  @GetMapping("/register")
  public String getRegisterPage(@Nonnull Model model) {
    model.addAttribute(MODEL_REG_FORM_ATTR, new RegistrationModel(null, null, null));
    return REGISTRATION_VIEW_NAME;
  }

  @PostMapping(value = "/register")
  public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel,
                             Errors errors,
                             Model model,
                             HttpServletResponse response,
                             HttpSession session) {
    if (!errors.hasErrors()) {
      final String registeredUserName;
      try {
        registeredUserName = userService.registerUser(
            registrationModel.username(),
            registrationModel.password()
        );
        response.setStatus(HttpServletResponse.SC_CREATED);
        model.addAttribute(MODEL_USERNAME_ATTR, registeredUserName);
      } catch (DataIntegrityViolationException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        addErrorToRegistrationModel(
            registrationModel,
            model,
            "username", "Username `" + registrationModel.username() + "` already exists"
        );
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    if (sessionValidator.isAndroidOauthSession(session)) {
      model.addAttribute(MODEL_FRONT_URI_ATTR, mobileCustomScheme + androidAppUri + Callbacks.Android.login);
      model.addAttribute(MODEL_FINAL_STEP_BTN_TEXT_ATTR, "Back to Application");
    } else {
      model.addAttribute(MODEL_FRONT_URI_ATTR, nifflerFrontUri + Callbacks.Web.init);
    }
    return REGISTRATION_VIEW_NAME;
  }

  private void addErrorToRegistrationModel(@Nonnull RegistrationModel registrationModel,
                                           @Nonnull Model model,
                                           @Nonnull String fieldName,
                                           @Nonnull String error) {
    BeanPropertyBindingResult errorResult = (BeanPropertyBindingResult) model.getAttribute(REG_MODEL_ERROR_BEAN_NAME);
    if (errorResult == null) {
      errorResult = new BeanPropertyBindingResult(registrationModel, "registrationModel");
    }
    errorResult.addError(new FieldError("registrationModel", fieldName, error));
  }
}
