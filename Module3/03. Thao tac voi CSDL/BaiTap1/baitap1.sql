use quanlysinhvien;
show tables; 
select * from student
WHERE studentname like 'h%';
select * from class
WHERE startdate like '_____12%';
select * from class
where month(startdate) =12;
select * from subject
where Credit between 3 and 5;
select* from student;
select* from subject;
select * from student where studentName='Hung';
update student set classID = 2 where studentid = 1;
select * from mark;
select s.StudentName,sub.SubName,m.Mark from student s
join mark m on s.studentid =m.studentid
join subject sub on sub.subid=m.subid
order by m.mark desc,s.studentname asc;









