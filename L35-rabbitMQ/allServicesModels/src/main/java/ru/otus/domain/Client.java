package ru.otus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "verification_status")
    @Enumerated(value = EnumType.STRING)
    private VerificationStatus verificationStatus;

    public Client() {
        this.verificationStatus = VerificationStatus.IN_PROGRESS;
    }

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
        this.verificationStatus = VerificationStatus.IN_PROGRESS;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}
