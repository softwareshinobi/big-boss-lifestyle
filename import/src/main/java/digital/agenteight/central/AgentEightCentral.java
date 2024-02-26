package digital.agenteight.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "digital.softwareshinobi")
//@ComponentScan
@SpringBootApplication
public class AgentEightCentral {

    public static void main(final String[] commandLineArgs) {
        
        SpringApplication.run(AgentEightCentral.class, commandLineArgs);
        
    }

}
