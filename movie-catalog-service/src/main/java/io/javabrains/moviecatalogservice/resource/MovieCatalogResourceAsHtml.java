package io.javabrains.moviecatalogservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieCatalogResourceAsHtml {

  @GetMapping("/hello")
  public String getMovieCatalog(){
    return "Hello World";
  }

}
