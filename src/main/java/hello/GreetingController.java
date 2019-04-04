package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// New in spring 4, returns domain instead of view? Merges @Controller and @ResponseBody
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // If only value supplied, will route all http methods by default
    @RequestMapping(value = "/greeting", method = { RequestMethod.GET, RequestMethod.POST })
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        // Greeting is automatically converted to JSON by
        // MappingJackson2HttpMessageConverter as Jackson 2 is in the build path
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}