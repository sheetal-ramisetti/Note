package com.user

import note.Note
import org.mysecurerest.User

/**
 * Created by sramisetti on 6/9/2017.
 */
class GUser extends User {

//        Integer id
        String firstName
        String lastName
        String email
        String password
        String userName

        static hasMany = [notes : Note]




        static constraints = {

           firstName maxSize: 20, nullable: false, blank: false, matches: "[a-zA-Z]+"
           lastName maxSixe: 20, nullable: false, blank: false, matches: "[a-zA-Z]+"
            userName maxSize: 20, nullable: false, blank: false
            email maxSize: 20, nullable: false, blank: false, email: true
            password maxSize: 6, blank: false
        }


    }


