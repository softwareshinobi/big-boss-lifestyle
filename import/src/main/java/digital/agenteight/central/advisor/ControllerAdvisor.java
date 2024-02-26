package digital.agenteight.central.advisor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {

        var modelAndView = new ModelAndView();
        
        modelAndView.addObject("message", ex.getMessage());
        
        modelAndView.setViewName("error/404");

        return modelAndView;
        
    }
    
}