package badblues.timetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

   
//TODO error handling
//TODO full stack of commands
//TODO readme installation guide, description and commands
//TODO make configuration and clean up

@SpringBootApplication
public class TimetrackerApplication implements ApplicationRunner{

	private static final Logger logger = LogManager.getLogger(TimetrackerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TimetrackerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
			logger.debug("Debugging log");
			logger.info("Info log");
			logger.warn("Warning.");
			logger.error("Error.");
			logger.fatal("Fatal error.");
	}

}
