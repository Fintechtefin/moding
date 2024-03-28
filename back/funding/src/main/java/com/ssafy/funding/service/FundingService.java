package com.ssafy.funding.service;

import com.ssafy.funding.repository.FundingRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FundingService {

    private final FundingRepository fundingRepository;

    public List<FundingRepository.FundingListResponseInterface> getFundingList(String status) {
        List<FundingRepository.FundingListResponseInterface> fundingListResponseInterfaceList =
                new ArrayList<>();

        switch (status) {
            case "progress":
                fundingListResponseInterfaceList = fundingRepository.getProgressRanking();
                break;
            case "request":
                fundingListResponseInterfaceList = fundingRepository.getRequestRanking();
                break;
        }

        return fundingListResponseInterfaceList;
    }
}
