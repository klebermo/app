package org.kleber.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  @RequestMapping("/")
  public String home() {
    return "index";
  }

  @RequestMapping("/signin")
  public String signin() {
    return "signin";
  }

  @RequestMapping("/signup")
  public String signup() {
    return "signup";
  }
}
