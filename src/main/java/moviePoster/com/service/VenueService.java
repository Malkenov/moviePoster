package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.request.VenueRequestDto;
import moviePoster.com.dto.response.VenueResponseDto;
import moviePoster.com.entity.Venue;
import moviePoster.com.mapper.VenueMapper;
import moviePoster.com.repository.VenueRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    public VenueResponseDto createVenue(VenueRequestDto dto){
        Venue venue = venueMapper.toEntity(dto);
        Venue save = venueRepository.save(venue);
        return venueMapper.toDto(save);
    }
}
