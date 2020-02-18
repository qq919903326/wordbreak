package com.wordbreak.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;
//日志切面类
@Aspect
@Component
public class LogAspects {
	@Pointcut("execution(public * com.wordbreak.controller.*.*(..))")
	public void pointCut(){};
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint){System.out.println(joinPoint.getSignature().getName()+"运行前....参数列表是:{"+Arrays.asList(joinPoint.getArgs())+"}"); }
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
}
