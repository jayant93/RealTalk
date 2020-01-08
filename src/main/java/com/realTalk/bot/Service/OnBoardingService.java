package com.realTalk.bot.Service;

import com.realTalk.bot.Model.Channel;
import com.realTalk.bot.Model.InstantMessagingData;
import com.realTalk.bot.Model.Member;
import com.realTalk.bot.Repositories.ChannelRepository;
import com.realTalk.bot.Repositories.InstantMessagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class OnBoardingService {

    @Autowired
    ChannelRepository channelRepo;

    @Autowired
    InstantMessagingRepository instantMessagingRepo;


    public List<InstantMessagingData> getUserByChannelId(String channelId) {

        List<InstantMessagingData> imsList = new ArrayList<>();
        Optional<Channel> channel = channelRepo.findById(channelId);
        for(int i=0;i<channel.get().getMembers().size();i++){
            InstantMessagingData imsData = new InstantMessagingData();
            imsData = instantMessagingRepo.findByUser(channel.get().getMembers().get(i).getId());
            if(imsData == null){
                continue;
            }else{
                imsList.add(imsData);
            }


        }
        return imsList;
    }

}
