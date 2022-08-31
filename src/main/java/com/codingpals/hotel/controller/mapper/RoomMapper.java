package com.codingpals.hotel.controller.mapper;

import com.codingpals.hotel.model.Room;
import com.codingpals.hotel.model.rest.CreateRoom;
import com.codingpals.hotel.service.BookingService;
import com.codingpals.hotel.service.RoomCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoomMapper {
  private final RoomCategoryService roomCategoryService;
  private final BookingService bookingService;
  public com.codingpals.hotel.model.rest.Room toRest(Room room) {
    return com.codingpals.hotel.model.rest.Room.builder()
        .id(room.getId())
        .categoryName(room.getCategory().getCategoryName())
        .price(room.getCategory().getPrice())
        .bookingCount(bookingService.getBookingCount(room.getId()))
        .status(room.getStatus())
        .build();
  }

  public Room toDomain(com.codingpals.hotel.model.rest.Room room) {
    return Room.builder()
        .id(room.getId())
        .category(roomCategoryService.getRoomCategoryByName(room.getCategoryName()))
        .status(room.getStatus())
        .build();
  }

  public Room toDomain(CreateRoom toCreate) {
    return Room.builder()
        .category(roomCategoryService.getRoomCategoryByName(toCreate.getCategoryName()))
        .status(Room.Status.AVAILABLE)
        .build();
  }
}