package com.javaspring.HotelProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "check_In")
    private LocalDate checkInDate;

    @Column(name = "check_Out")
    private LocalDate checkOutDate;

    @Column(name = "guest_fullName")
    private String guestFullName;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "children")
    private int NumOfChildren;

    @Column(name = "adults")
    private int NumOfAdults;

    @Column(name = "total_guest")
    private int totalNumOfGuest;


    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY) //relation khong duoc tai truc tiep tu co so du lieu
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumberOfGuest(){
        this.totalNumOfGuest = this.NumOfAdults + NumOfChildren;
    }
    public void setNumOfChildren(int numOfChildren){
        NumOfChildren= numOfChildren;
        calculateTotalNumberOfGuest();
    }
    public void setNumOfAdults(int numOfAdults){
        NumOfAdults= numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public BookedRoom(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}