package com.blogapi.blogapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@OpenAPIDefinition(info = @Info(title = "Code-First Approach (reflectoring.io)", description = "" +
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et rhoncus quam. Aenean quis augue ac eros pulvinar malesuada. "
        +
        "In sagittis elit egestas tincidunt iaculis. " +
        "Donec eu lacus vitae nulla varius consectetur a vel quam. Aliquam erat volutpat. Duis eget ullamcorper tellus", contact = @Contact(name = "Reflectoring", url = "https://reflectoring.io", email = "petros.stergioulas94@gmail.com"), license = @License(name = "MIT Licence", url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")), servers = @Server(url = "http://localhost:8080"))
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
class OpenAPIConfiguration {
}