package com.javaspring.HotelProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private BigDecimal RoomPrice;
    private boolean isBooked=false;

    @Lob
    private Blob photo;
    @OneToMany(mappedBy="room", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //cascade có tác dụng lan truyền các thaydđôi từ đối tượng gốc(ví dụ khi room bị xóa thì history room cũng bị xóa)
    private List<BookedRoom> bookings;
    public Room(){
        this.bookings=new ArrayList<>();
    }
    public void addBooking(BookedRoom booking){
        if(bookings == null){
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
        booking.setRoom(this); //setRoom được tạo ra nhờ thư viện lombok
        isBooked=true;
        String bookingCode= RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode);
    }
}
