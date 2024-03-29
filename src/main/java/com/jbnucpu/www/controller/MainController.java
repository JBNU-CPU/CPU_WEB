package com.jbnucpu.www.controller;

import com.jbnucpu.www.entity.UserEntity;
import com.jbnucpu.www.repository.UserRepository;
import com.jbnucpu.www.service.AuthService;
import com.jbnucpu.www.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserRepository userRepository;

    private final AuthService authService;
    private final CalendarService calendarService;

    @GetMapping("/")
    public String mainPage(Model model){

        //현재 로그인한 사용자, 로그인 버튼 - 준혁
        if(authService.isAuthenticated()){
            model.addAttribute("roginUserName",authService.getUserStudentnumber());
            model.addAttribute("activateLogoutButton",true);
            model.addAttribute("id", authService.getUserId());
        }
        else{
            model.addAttribute("roginUserName","로그인하지 않음");
            model.addAttribute("activateLogoutButton",false);
            model.addAttribute("id", null);
        }

        // 테스트 때문에 썼습니다 나중에 지우겠습니다 - 도현
        if(authService.isAuthenticated()){
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

            String studentNumber = SecurityContextHolder.getContext().getAuthentication().getName();


            UserEntity loginUser = userRepository.findByStudentnumber(studentNumber);
            if(loginUser !=null) {
                System.out.println(loginUser.getName());

                return "main";
            }
            else return "main";
        }

        model.addAttribute("CALENDAR", calendarService.findAllSchedule());

        return "main";
    }
}
