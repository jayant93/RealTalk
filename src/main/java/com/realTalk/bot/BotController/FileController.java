package com.realTalk.bot.BotController;


import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController("File")
public class FileController {

    @PostMapping("/Topics")
    public String uploadcsv(@RequestBody File file){
        return  file.getAbsolutePath();
    }

    @GetMapping("/Test")
    public String test(){
        return "Reached File controller";
    }

}
