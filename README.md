# COS730ATLPeople
An updated eclipse ATLPeopleCOS730 project that can be cloned and compiled.

-----------------------------------------Git-------------------------------------------------------------------------------------------------------------------------------------------------

Basic git tutorial :
https://confluence.atlassian.com/bitbucketserver/basic-git-commands-776639767.html

Start here : 
run git clone https://github.com/ndlamini/COS730ATLPeople.git   : this create a local repository.
The above command must be run once and never run it again.

After running this command .
A folder named COS730ATLPeople  will be created in your local drive (C), try locate it.
Inside this folder there is workspaceCOS730 ,this is a eclipse project you can open it using eclipe IDE,
If you deleted this folder by mistake or intentional, you have to run :git clone git clone https://github.com/ndlamini/COS730ATLPeople.git

Before you run the commands below, be sure that the changes you have made are correct , no compile errors.

Useful Git Commands
Command.        	Explaination.
git pull                First commad to run so that you get the updated version from remote repository. 
git status :    	checks to see if you have files that need to be pushed to git , red highlighted files indicates that you have made changes and you did not commit your changes.
git add <pathtofile>    you adding file that you have changed. <pathtofile> is the absolute path to the file you are adding. you can repalce it with * to indicate that you adding everything 
git commit -m "message" you are committing the changes you have made to your local repository
git pull                again get the latest version ,aome in the group might have pushed some changes before you
git push  you are pushing your changes to the remote reposistory


---------------------------------------Project File Structure---------------------------------------------------------------------------------------------------------------------------------

The project mentioned contians COS301 People project to be tested.
 
  - src/main/java/defaultimpl contains the source code.
  - scr/test/java/ATLPeople contains the unittest class, TestRunner.java runs all the unittest classes created.
  - It will print on console all failed test ,if any.

----------------------------------------Unitetest java files----------------------------------------------------------------------------------------------------------------------------------

The following files are already created ,and passed to TesRunner.java to run all the files ,if you will create a new file masure you pass it to this file.

1. GroupImplementationTest.java

    Test Cases 
     -Functional Test cases
        a) Declaring and instatiating a Group object :using assertNotNull() to check group created is not null.
	b) Add entities to group:  creating three objects of type person a,b,c and adding them to group the group.
           check ismember() : using assertTrue,calling method: isMember(Person a) check if one of the person a added above is a member of the group :
	
	 
	
2.  




		                                             

