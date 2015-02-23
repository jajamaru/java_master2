/**
 * 
 */
package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Romain
 *
 */
@Controller
@RequestMapping("/crud")
public class CrudController {

  @RequestMapping(method = RequestMethod.GET)
  public String hello() {
    return "index";
  }

}
