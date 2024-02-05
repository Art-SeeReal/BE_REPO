package com.ArtSeeReal.pro.etc;

import com.ArtSeeReal.pro.repository.delete.BannerDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.BoardDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.ComplaintDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.FileDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.IntroduceDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.RequestCommentDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.RequestDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.SnsDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.UserDeleteRepository;
import com.ArtSeeReal.pro.repository.delete.UserObjDeleteRepository;
import com.ArtSeeReal.pro.repository.history.BannerHistoryRepository;
import com.ArtSeeReal.pro.repository.history.BoardHistoryRepository;
import com.ArtSeeReal.pro.repository.history.FileHistoryRepository;
import com.ArtSeeReal.pro.repository.history.IntroduceHistoryRepository;
import com.ArtSeeReal.pro.repository.history.RequestCommentHistoryRepository;
import com.ArtSeeReal.pro.repository.history.RequestHistoryRepository;
import com.ArtSeeReal.pro.repository.history.SnsHistoryRepository;
import com.ArtSeeReal.pro.repository.history.UserHistoryRepository;
import com.ArtSeeReal.pro.repository.main.BannerRepository;
import com.ArtSeeReal.pro.repository.main.BoardRepository;
import com.ArtSeeReal.pro.repository.main.ComplaintRepository;
import com.ArtSeeReal.pro.repository.main.FileRepository;
import com.ArtSeeReal.pro.repository.main.IntroduceRepository;
import com.ArtSeeReal.pro.repository.main.RequestCommentRepository;
import com.ArtSeeReal.pro.repository.main.RequestRepository;
import com.ArtSeeReal.pro.repository.main.SnsRepository;
import com.ArtSeeReal.pro.repository.main.UserObjRepository;
import com.ArtSeeReal.pro.repository.main.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Uid {
    private BannerDeleteRepository bannerDeleteRepository;
    private BoardDeleteRepository boardDeleteRepository;
    private ComplaintDeleteRepository complaintDeleteRepository;
    private FileDeleteRepository fileDeleteRepository;
    private IntroduceDeleteRepository IntroduceDeleteRepository;
    private RequestCommentDeleteRepository requestCommentDeleteRepository;
    private RequestDeleteRepository requestDeleteRepository;
    private SnsDeleteRepository snsDeleteRepository;
    private UserDeleteRepository userDeleteRepository;
    private UserObjDeleteRepository userObjDeleteRepository;
    private BannerHistoryRepository bannerHistoryRepository;
    private BoardHistoryRepository boardHistoryRepository;
    private FileHistoryRepository fileHistoryRepository;
    private IntroduceHistoryRepository introduceHistoryRepository;
    private RequestCommentHistoryRepository requestCommentHistoryRepository;
    private RequestHistoryRepository requestHistoryRepository;
    private SnsHistoryRepository snsHistoryRepository;
    private UserHistoryRepository userHistoryRepository;
    private BannerRepository bannerRepository;
    private BoardRepository boardRepository;
    private ComplaintRepository complaintRepository;
    private FileRepository fileRepository;
    private IntroduceRepository introduceRepository;
    private RequestCommentRepository requestCommentRepository;
    private RequestRepository requestRepository;
    private SnsRepository snsRepository;
    private UserRepository userRepository;
    private UserObjRepository userObjRepository;

    public String UidCreator(){
        String uid = UUID
                .randomUUID()
                .toString()
                .replaceAll("-","");

        if( !bannerDeleteRepository.existsByUid(uid) &&
            !boardDeleteRepository.existsByUid(uid) &&
            !complaintDeleteRepository.existsById(uid) &&
            !fileDeleteRepository.existsById(uid) &&
            !IntroduceDeleteRepository.existsById(uid) &&
            !requestCommentDeleteRepository.existsByUid(uid) &&
            !requestDeleteRepository.existsById(uid) &&
            !snsDeleteRepository.existsById(uid) &&
            !userDeleteRepository.existsById(uid) &&
            !userObjDeleteRepository.existsById(uid) &&
            !bannerHistoryRepository.existsById(uid) &&
            !boardHistoryRepository.existsById(uid) &&
            !fileHistoryRepository.existsById(uid) &&
            !introduceHistoryRepository.existsById(uid) &&
            !requestCommentHistoryRepository.existsById(uid) &&
            !requestHistoryRepository.existsById(uid) &&
            !snsHistoryRepository.existsById(uid) &&
            !userHistoryRepository.existsById(uid) &&
            !bannerRepository.existsById(uid) &&
            !boardRepository.existsById(uid) &&
            !complaintRepository.existsById(uid) &&
            !fileRepository.existsById(uid) &&
            !introduceRepository.existsById(uid) &&
            !requestCommentRepository.existsById(uid) &&
            !requestRepository.existsById(uid) &&
            !snsRepository.existsById(uid) &&
            !userRepository.existsById(uid) &&
            !userObjRepository.existsById(uid)){

            return uid;
        }else{
            return UidCreator();
        }
    }
}
