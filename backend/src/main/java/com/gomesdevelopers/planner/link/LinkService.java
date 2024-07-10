package com.gomesdevelopers.planner.link;

import com.gomesdevelopers.planner.activity.Activity;
import com.gomesdevelopers.planner.activity.ActivityData;
import com.gomesdevelopers.planner.activity.ActivityRequestPayload;
import com.gomesdevelopers.planner.activity.ActivityResponse;
import com.gomesdevelopers.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public LinkResponse registerLink(LinkRequestPayload payload, Trip trip){
        Link newLink = new Link(payload.title(), payload.url(), trip);

        this.repository.save(newLink);

        return new LinkResponse(newLink.getId());
    }

    public List<LinkData> getAllLinksFromTrip(UUID tripId){
        return this.repository.findByTripId(tripId).stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}
