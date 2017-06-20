package note

import com.user.User

class Note {

    Integer id
    String text
//  User user



    static belongsTo = [user : User]

    static constraints = {
        text maxSize: 20
    }
}
