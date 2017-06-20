package note

import com.user.GUser

class Note {

    Integer id
    String text
//  GUser user



    static belongsTo = [user : GUser]

    static constraints = {
        text maxSize: 20
    }
}
