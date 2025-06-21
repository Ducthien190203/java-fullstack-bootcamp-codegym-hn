use quanlysinhvien;
show tables;
SELECT 
    *
FROM
    subject
WHERE
    credit = (SELECT 
            MAX(credit)
        FROM
            subject);
SELECT 
    *
FROM
    mark;
SELECT 
    *
FROM
    subject sub
        JOIN
    mark m ON sub.subid = m.subid
WHERE
    m.mark = (SELECT 
            MAX(mark)
        FROM
            mark);
show tables;
SELECT 
    *
FROM
    mark;
SELECT 
    s.studentid, s.studentname, AVG(mark)
FROM
    student s
        JOIN
    mark m ON s.studentid = m.studentid
GROUP BY s.studentid , s.studentname
ORDER BY AVG(mark) DESC;
describe mark;
SELECT 
    *
FROM
    mark;
insert into mark(subid,studentid,mark,examtimes) values
(2,2,5,1);