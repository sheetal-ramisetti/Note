package note

import com.user.User
import grails.transaction.Transactional

@Transactional
class NoteService {

    Note createNote(Map map, Integer userId){

        User user = User.findById(userId)

        if(user!= null){

            Note note = new Note()
            note.properties = map
            note.user = user
            note.validate()
            if(note.hasErrors()){
                note.discard()

            }else{
                note.save()
            }
            return note

        }
        return null
    }


    //I told you what the return type on this should be
    boolean deleteNote(Integer userId, Integer noteId){

        User user = User.findById(userId)

        if(user!=null){

            Note note = Note.findByIdAndUser(noteId,user)

            if(note!=null){

                note.delete()
                return true
            }else{
                return false
            }


        }else{
            return false
        }
    }
    Note updateNote(Integer userId,Integer noteId,Map map){

        User user = User.findById(userId)

        if(user!= null){

            Note note = Note.findByIdAndUser(noteId, user)

            if(note!=null){
                note.properties = map
                note.user = user
                note.validate()
                if(note.hasErrors()){
                    note.discard()
                }else{
                    note.save()
                }
                return note
            }
        }else{
            return null
        }
    }

    List listNotes(Integer userId){
        User user = User.findById(userId)
        if (user != null) {
            List notes = Note.findAllByUser(user)
            return notes
        } else {
            return null
        }

    }
    Note getById(Integer userId, Integer noteId){
        User user = User.findById(userId)
        if (user != null) {
            Note note = Note.findByIdAndUser(noteId, user)
            return note
        } else {
            return null
        }
    }

}
