package org.kleber.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Date;

@Controller
public class FailureController implements ErrorController  {
    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(Model model, WebRequest request, HttpServletResponse response) {
        Map<String, Object> msg = errorAttributes.getErrorAttributes(request, true);

        model.addAttribute("status", response.getStatus());
        model.addAttribute("error", (String) msg.get("error"));
        model.addAttribute("message", (String) msg.get("message"));
        model.addAttribute("timestamp", (Date) msg.get("timestamp"));
        model.addAttribute("trace", (String) msg.get("trace"));

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
