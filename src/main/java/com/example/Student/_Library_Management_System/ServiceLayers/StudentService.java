package com.example.Student._Library_Management_System.ServiceLayers;

import com.example.Student._Library_Management_System.DTOs.StudentMobileUpdateDTO;
import com.example.Student._Library_Management_System.Enums.CardStatus;
import com.example.Student._Library_Management_System.Model_Layers.Card;
import com.example.Student._Library_Management_System.Model_Layers.Student;
import com.example.Student._Library_Management_System.Repositories.StudentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepositories studentRepositories;


    public String createStudent(Student student){

        Card card=new Card();
        card.setCardStatus(CardStatus.ACTIVATED); //This is how you set the enum values
        card.setStudentVariableName(student); //To set foreign key column attribute value

        /** As we now need to generate our child class also and that can be done
         by our parent class which is below */
         student.setCard(card);

        //if there was a unidirectional mapping in that case we have to save both of them\\


        //but we are super advance and we are using bidirectional mapping
        studentRepositories.save(student);
        //I'm saving the student so by cascading effect the child will automatically be saved\\
        return "Student and card added";
    }
    public String getStudent(int id){
       Student student=studentRepositories.findById(id).get();
       return student.getName();
    }
    public String findNameByEmail(String email){
        //We Don't have custom query  by which we can get our email
        //Defining the query in StudentRepositories
        //Then doing the below functions
        Student student=studentRepositories.findByEmail(email);
        return student.getName();
    }
    public String updateMob(StudentMobileUpdateDTO studentMobileUpdateDTO){

        /**Convert the DTO to entity**/


        //First I will try to fetch the original data
        Student originalStudent = studentRepositories.findById(studentMobileUpdateDTO.getId()).get();

        //We will keep the other properties as it is: and only change the required parameters

        //1st Rule: Setting the original attributes as it is
        originalStudent.setMobNo(studentMobileUpdateDTO.getMobNo());
//        originalStudent.setId(newStudent.getId());
        //Then updating with the new values
        studentRepositories.save(originalStudent);

        return "Student Has been updated successfully";

    }
}
