package com.realTalk.bot.BotController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.realTalk.bot.DTO.ChannelDTO;
import com.realTalk.bot.Model.InstantMessagingData;
import com.realTalk.bot.Model.Member;
import com.realTalk.bot.Service.SlackWorkspaceInfoCollect;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/CollectInfo")
public class CollectWorkspaceInfo {

    @Autowired
    Environment environment;

    @Autowired
    SlackWorkspaceInfoCollect service;


    CollectWorkspaceInfo(Environment environment, SlackWorkspaceInfoCollect slackWorkspaceInfoCollect) {
        this.environment = environment;
        this.service = slackWorkspaceInfoCollect;
    }

    public static final Logger log = LoggerFactory.getLogger(SlackWorkspaceInfoCollect.class);

    @GetMapping
    public ResponseEntity<List<ChannelDTO>> collectWorkspaceInfo() throws IOException {

        final String uri = "https://slack.com/api/rtm.start?token=" + environment.getProperty("slackBotToken") + "&simple_latest&no_unreads";
        List<ChannelDTO> channelList = new ArrayList<>();
        List<Member> membersList = new ArrayList<>();
        List<InstantMessagingData> imsList = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            log.info("Collecting workspace information from url : " + uri);
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            log.info("Collected Information" + result);

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            JsonFactory factory = mapper.getFactory();
            JsonParser parser = factory.createParser(result.getBody());
            JsonNode actualObj = mapper.readTree(parser);
            JsonNode channels = actualObj.get("channels");
            JsonNode users = actualObj.get("users");
            JsonNode ims = actualObj.get("ims");


            for (int i = 0; i < channels.size(); i++) {
                ChannelDTO channel = new ChannelDTO();
                byte[] bytes = channels.get(i).toString().getBytes();
                channel = mapper.readValue(bytes, ChannelDTO.class);
                channelList.add(channel);
            }


            for (int i = 0; i < users.size(); i++) {
                Member member = new Member();
                byte[] bytes = users.get(i).toString().getBytes();
                member = mapper.readValue(bytes, Member.class);
                membersList.add(member);
            }

            for (int i = 0; i < ims.size(); i++) {
                InstantMessagingData imsData = new InstantMessagingData();
                byte[] bytes = ims.get(i).toString().getBytes();
                imsData = mapper.readValue(bytes, InstantMessagingData.class);
                imsList.add(imsData);

            }

            System.out.println(result);

            log.info("Sending user information to Service for saving it to db");
            service.saveUserInfo(membersList);
            service.saveInstantMessageData(imsList);            //            service.saveChannelInfo(channelList);
            service.saveWorkspaceInfo(channelList, membersList);
        }
        catch (Exception e) {
            log.info("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<List<ChannelDTO>>(channelList, HttpStatus.CREATED);
    }


}
