package com.wordbreak.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;
//日志切面类
@Aspect
@Component
public class LogAspects {
	@Pointcut("execution(public * com.wordbreak.web.*.*(..))")
	public void pointCut(){};
	//@before代表在目标方法执行前切入, 并指定在哪个方法前切入
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint){
		System.out.println(joinPoint.getSignature().getName()+"运行前....参数列表是:{"+Arrays.asList(joinPoint.getArgs())+"}");
	}
	@After("pointCut()")
	public void logEnd(JoinPoint joinPoint){
		System.out.println(joinPoint.getSignature().getName()+"运行结束......");
	}
	@AfterReturning(value="pointCut()",returning="result")
	public void logReturn(Object result){
		System.out.println("正常返回......运行结果是:{"+result+"}");
	}
	@AfterThrowing(value="pointCut()",throwing="exception")
	public void logException(Exception exception){
		System.out.println("运行异常......异常信息是:{"+exception+"}");
	}
	/*@Around("pointCut()")
	public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		System.out.println("@Arount:执行目标方法之前...");
		Object obj = proceedingJoinPoint.proceed();//相当于开始调div地
		System.out.println("@Arount:执行目标方法之后...");
		return obj;
	}*/
}
