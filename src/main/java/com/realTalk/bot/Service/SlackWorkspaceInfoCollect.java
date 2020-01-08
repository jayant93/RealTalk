package com.realTalk.bot.Service;

import com.realTalk.bot.DTO.ChannelDTO;
import com.realTalk.bot.Model.Channel;
import com.realTalk.bot.Model.InstantMessagingData;
import com.realTalk.bot.Model.Member;
import com.realTalk.bot.Repositories.ChannelRepository;
import com.realTalk.bot.Repositories.InstantMessagingRepository;
import com.realTalk.bot.Repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SlackWorkspaceInfoCollect {

    public static final Logger log = LoggerFactory.getLogger(SlackWorkspaceInfoCollect.class);

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    InstantMessagingRepository instantMessageRepo;

    @Autowired
    ChannelRepository channelRepo;

    public void saveUserInfo(List<Member> members){
        log.info("Saving user details in  a workspace to DB");

            try {
                for(int i=0;i<members.size();i++){
                    Member member = members.get(i);
                    if(member.getProfile().getEmail() == null){
                        member.getProfile().setEmail(member.getId()+"@BOT");
                    }else {
                        //Do Nothing
                    }
                    memberRepo.save(member);
                }
             //   memberRepo.saveAll(members);
            log.info("User Information Saved");
            }catch (Exception e){
                log.error(e.getLocalizedMessage());
                e.printStackTrace();
            }
    }

    public void saveChannelInfo(List<ChannelDTO> channelDto){



        log.info("Saving Channel info");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    try {

        for (int i = 0; i < channelDto.size(); i++) {
            Channel channel = new Channel();
            List<Member> members = new ArrayList<>();
            if(channelDto.get(i).getMembers() == null){
                channel = mapper.map(channelDto.get(i), Channel.class);
                channelRepo.save(channel);
                continue;
            }
            for (int j = 0; j < channelDto.get(i).getMembers().size(); j++) {
                Optional<Member> member = memberRepo.findById(channelDto.get(i).getMembers().get(j));
                if (member.isPresent()) {
                    members.add(member.get());
                } else {
                    continue;
                }
            }

            channel = mapper.map(channelDto.get(i), Channel.class);
            channel.setMembers(null);
            channel = channelRepo.save(channel);

           for(int z = 0;z<members.size();z++){
                members.get(z).setChannel(Arrays.asList(channel));
                memberRepo.save(members.get(z));
            }
        }
    }catch(Exception e){
        log.info(e.getLocalizedMessage());
        e.printStackTrace();
    }

    }

    public void saveInstantMessageData(List<InstantMessagingData> ims){
        log.info("Saving IMS Details .... ");
        instantMessageRepo.saveAll(ims);
        log.info("Ims Details Saved Successfully..");
    }

    public void saveWorkspaceInfo(List<ChannelDTO> channelDTOS,List<Member> members){

        log.info("Saving Channel info");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        try {
            for (int i = 0; i < channelDTOS.size(); i++) {
                Channel channel = new Channel();
                List<Member> channelMembers = new ArrayList<>();

                if(channelDTOS.get(i).getMembers() == null){
                    channel = mapper.map(channelDTOS.get(i), Channel.class);
                    channelRepo.save(channel);
                    continue;
                }
                channel = mapper.map(channelDTOS.get(i), Channel.class);


                for (int j = 0; j < channelDTOS.get(i).getMembers().size(); j++) {
                    for (int k = 0; k < members.size(); k++) {
                        if (channelDTOS.get(i).getMembers().get(j).equalsIgnoreCase(members.get(k).getId())) {
                            members.get(k).setEmail(members.get(k).getProfile().getEmail());
                            members.get(k).setFirst_name(members.get(k).getProfile().getFirst_name());
                            members.get(k).setLast_name(members.get(k).getProfile().getLast_name());
                            members.get(k).setPhone(members.get(k).getProfile().getPhone());
                            channelMembers.add(members.get(k));
                        } else {
                            continue;
                        }
                    }
                }
                channel.setMembers(channelMembers);
                channelRepo.save(channel);


            }
        }catch (Exception e){
            log.error("Data not Saved Reason : "+e.getLocalizedMessage());
        }

    }


}
