package ru.job4j.model.user;

import ru.job4j.model.EntityModel;
import ru.job4j.model.role.Role;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class Account implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private Role role;

    private String password;
    private String name;
    private String phone;

    @Column(unique = true)
    private String email;

    public Account() {
    }

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        try {
            this.password = MD5.createHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public boolean validatePwd(String enteredPwd) {
        try {
            return MD5.validateString(enteredPwd, password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private static class MD5 {

        public static String createHash(String s) throws NoSuchAlgorithmException {
            MessageDigest alg = MessageDigest.getInstance("MD5");
            alg.reset();
            alg.update(s.getBytes());
            byte[] bytes = alg.digest();
            BigInteger nr = new BigInteger(1, bytes);
            return nr.toString(16);
        }

        public static boolean validateString(String str, String hash)
                throws NoSuchAlgorithmException {
            return hash.equals(createHash(str));
        }
    }
}
