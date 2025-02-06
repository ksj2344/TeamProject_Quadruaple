package com.green.project_quadruaple.alarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("alram")
public class AlarmController {
    private final AlarmService alarmService;

//    @GetMapping("home")
//    public SseEmitter unconfirmCnt(){
//        SseEmitter emitter=new SseEmitter();
//        new Thread(()->{
//           while(true){
//               try{
//                   emitter.send(SseEmitter.event().data(null));
//               } catch (IOException e){
//                   throw new RuntimeException(e);
//               }
//               Thread.sleep(1500);
//           }
//        });

//        return emitter;
//    }
}
