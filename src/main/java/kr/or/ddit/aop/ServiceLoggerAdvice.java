package kr.or.ddit.aop;

import java.util.Arrays;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import kr.or.ddit.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;

/* AOP(Aspect Oriented Programming)
 - 횡단 관심사(Cross-Cutting Concern) : 핵심(core) 비즈니스 로직(삼겹살구어먹기)과 
 				다소 거리가 있지만, 여러 모듈에서 공통적으로 반복적인 처리를 요구하는 내용(불판교체, 불판닦기, 콜라교체)
 - 횡단 관심사 분리(Separation Of Cross-Cutting Concern) : 횡단관심사에 해당하는
 				부분(불판닦게, 불판교체, 콜라교체)을 분리해서 한 곳으로 모으는 것을 의미
 - Aspect(애스팩트) : AOP의 단위가 되는 횡단 관심사(불판교체, 불판닦기, 콜라교체)
 - Join Point(조인포인트) : 횡단 관심사가 실행될 지점이나 시점(불판에 탄 찌꺼기가 많을 때, 콜라를 다 마셨을 때)
 - Pointcut(포인트컷) : 수많은 조인 포인트 중에서 선별하기 위한 표현식
 					- 불필요 : 고기를 불판에 올린다, 김치를 굽는다. 고기를 뒤집는다. 콜라 뚜껑을 땄다
 					- 필요 : 불판에 탄 찌꺼기가 많을 때, 콜라를 다 마셨을 때
 - Weaving(위빙) : 애플리케이션 코드의 적절한 지점에 애스팩트(횡단 관심사)를 적용하는 것
 - Advice(어드바이스) : 특정 조인 포인트에서 실행되는 코드로 횡단 관심사를 실제로 구현해서 처리하는 부분
 - Component : 골뱅이Aspect의 짝궁. 
 	root-context.xml에서 <context:component-scan base-package="kr.or.ddit.aop"></context:component-scan>
 	스캔 시 "여기 봐주세요"라는 의미
 */
@Slf4j
@Aspect
@Component
public class ServiceLoggerAdvice {
	//포인트컷 표현식. 별쩜쩜별괄호쩜쩜괄호
	//execution : 포인트컷(대상(메소드)을 선별하는 것) 지정자
	// *  : 임의의 1개(동일레벨)
	// .. : 임의의 0개 이상(하위레벨 포함)
	//kr.or.ddit.*..*(..) : 
	//			 패키지 밑의 각각의 패키지가 있고
	//			 그 하위에 모든 파일/패키지
	//			 각각의 메소드가 있고
	//			 (..) : 모든 파라미터
	//Before어드바이스 : 조인 포인트 전에 실행됨. 예외가 발생하는 경우만 제외하고 항상 실행됨
	//포인트컷에 포함된 메서드를 대상으로 그 메서드가 실행되기 전에 로그를 출력해보자
	@Before("execution(* kr.or.ddit.*..*(..))")
	public void startLog(JoinPoint jp) {
		log.info("starLog");
		//.getSignature() : 어떤 클래스의 어떤 메서드가 실행되었는지 보여줌. 파라미터 타입은 무엇인지 보여줌
		// kr.or.ddit.service.create(ItemVO itemVO)
		log.info("startLog : " + jp.getSignature());
		//.getArgs() : 전달 된 파라미터 정보를 보여줌
		//[itemVO [itemNo=3,itemName=새우깡...]]
		log.info("startLog : " + Arrays.toString(jp.getArgs()));
	}
	
	//AfterReturning 어드바이스
	//조인 포인트가 정상적으로 종료한 후에 실행됨. 예외 발생 시 실행 안 됨
	@AfterReturning("execution(* kr.or.ddit.*..*(..))")
	public void logReturning(JoinPoint jp) {
		log.info("logReturning");
		//.getSignature() : 어떤 클래스의 어떤 메서드가 실행되었는지 보여줌. 파라미터 타입은 무엇인지 보여줌
		//int kr.or.ddit.mapper.MemberMapper.checkMemId(MemberVO)
		log.info("logReturning : " + jp.getSignature());
	}
	
	//AfterThrowing 어드바이스
	//조인 포인트에서 예외 발생 시 실행. 예외가 발생 안 되면 실행 안 됨
	@AfterThrowing(pointcut="execution(* kr.or.ddit.*..*(..))", throwing="e")
	public void logException(JoinPoint jp, Exception e) {
		log.info("logException");
		//.getSignature() : 어떤 클래스의 어떤 메서드가 실행되었는지 보여줌. 파라미터 타입은 무엇인지 보여줌		
		log.info("logException : " + jp.getSignature());
		//예외 메시지를 보여줌
		log.info("logException : " + e);
	}
	
	//After 어드바이스
	//조인 포인트 완료 후 실행. 예외 발생이 되더라도 항상 실행 됨
	@After("execution(* kr.or.ddit.*..*(..))")
	public void endLog(JoinPoint jp) {
		log.info("endLog");
		//.getSignature() : 어떤 클래스의 어떤 메서드가 실행되었는지 보여줌. 파라미터 타입은 무엇인지 보여줌		
		log.info("endLog : " + jp.getSignature());
		//.getArgs() : 전달 된 파라미터 정보를 보여줌
		//[itemVO [itemNo=3,itemName=새우깡...]]
		log.info("endLog : " + Arrays.toString(jp.getArgs()));
	}
	
	//ProceedingJoinPoint : Around 어드바이스에서 사용함
	//Around어드바이스 : 조인 포인트 전후에 실행됨
	@Around("execution(* kr.or.ddit.*..*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		//메소드 실행 직전 시간 체킹
		long startTime = System.currentTimeMillis();
		log.info("pjpStart : " + Arrays.toString(pjp.getArgs()));
		
		//메소드 실행
		Object result = pjp.proceed();
		
		//메소드 실행 직후 시간 체킹
		long endTime = System.currentTimeMillis();
		log.info("pjpEnd : " + Arrays.toString(pjp.getArgs()));
		
		//메소드 실행 시간 : 직후 시간 - 직전 시간
		log.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		
		return result;
	}
}









