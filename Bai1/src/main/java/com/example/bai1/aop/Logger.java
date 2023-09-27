package com.example.bai1.aop;

import com.example.bai1.model.Player;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
public class Logger {
    private static LocalDate time = LocalDate.now();
    private static int count =0;
    @AfterReturning("execution(* com.example.bai1.controller.PlayerController.playerActive(..))")
    public void changePlayerActiveSuccess(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Player player = (Player) args[0];
        System.out.println("-----------------------------"+player.getName()+ " change status at "+ time + "is Active "+"-----------------------");
    }

    @AfterReturning("execution(* com.example.bai1.controller.PlayerController.playerReserve(..))")
    public void changePlayerReserveSuccess(JoinPoint joinPoint){
        java.lang.Object[] args = joinPoint.getArgs();
        Player player = (Player) args[0];
        System.out.println("------------"+player.getName()+"change status at"+ time+"is Reserve"+"-------------");
    }

    @After("execution(* com.example.bai1.controller.PlayerController.playerActive(..))")
    public void countPlayer(){
        count++;
        System.out.println("Quantity Player Active is " + count);
    }
}
