package service;

import domain.Members;
import domain.MembersRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;

    public String create(String rgb, String filePath) {
    	Date date = new Date(); 
        Members members = new Members();
        members.setDATE(date);
        membersRepository.save(rgb, filePath);
        return "success!!";
    }
}
