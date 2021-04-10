package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger클래스의 인슨턴스 생성하기
	static Logger logger = Logger.getLogger(Log4jTest.class);

	public static void main(String[] args) {
		// log 기록 출력하기

		// 방법1
		logger.trace("이 메시지는 TRACE 레벨의 메시지입니다.");
		logger.debug("이 메시지는 debug 레벨의 메시지입니다.");
		logger.info("이 메시지는 Information 레벨의 메시지입니다.");
		logger.warn("이 메시지는 Warning 레벨의 메시지입니다.");
		logger.error("이 메시지는 Error 레벨의 메시지입니다.");
		logger.fatal("이 메시지는 Fatal 레벨의 메시지입니다.");

		// 방법2
//		logger.log(Level.FATAL, "이 메시지는 Level.Fatal 레벨의 메시지입니다.");
//		logger.log(Level.ERROR, "이 메시지는 Level.ERROR 레벨의 메시지입니다.");
//		logger.log(Level.WARN, "이 메시지는 Level.WARN 레벨의 메시지입니다.");
//		logger.log(Level.INFO, "이 메시지는 Level.INFO 레벨의 메시지입니다.");
//		logger.log(Level.DEBUG, "이 메시지는 Level.DEBUG 레벨의 메시지입니다.");
//		logger.log(Level.TRACE, "이 메시지는 Level.TRACE 레벨의 메시지입니다.");
	}

}
