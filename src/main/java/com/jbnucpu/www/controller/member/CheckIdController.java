package com.jbnucpu.www.controller.member;

import com.jbnucpu.www.dto.StudentNumberDTO;
import com.jbnucpu.www.service.CheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class CheckIdController {

    private final CheckService checkService;

    @PostMapping("/check/id")
    public ResponseEntity<Boolean> checkIdPage(@RequestBody StudentNumberDTO studentnumberDTO){

        String studentNumber = studentnumberDTO.getStudentnumber();

        return checkService.checkId(studentNumber);
    }

}
