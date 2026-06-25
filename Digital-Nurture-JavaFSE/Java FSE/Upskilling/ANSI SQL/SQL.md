##### **1. User Upcoming Events:**

\----------------------------------------------------------------------------------------------------------------------------

SELECT u.full\_name,

&#x20;      e.title,

&#x20;      e.city,

&#x20;      e.start\_date

FROM Users u

JOIN Registrations r ON u.user\_id = r.user\_id

JOIN Events e ON r.event\_id = e.event\_id

WHERE e.status = 'upcoming'

&#x20; AND u.city = e.city

ORDER BY e.start\_date;

\--------------------------------------------------------------------------------------------------------------------------

##### **2. Top Rated Events:**



SELECT e.event\_id,

&#x20;      e.title,

&#x20;      AVG(f.rating) AS avg\_rating,

&#x20;      COUNT(f.feedback\_id) AS feedback\_count

FROM Events e

JOIN Feedback f ON e.event\_id = f.event\_id

GROUP BY e.event\_id, e.title

HAVING COUNT(f.feedback\_id) >= 10

ORDER BY avg\_rating DESC;

\--------------------------------------------------------------------------------------------------------------------------

##### **3. Inactive Users**



SELECT \*

FROM Users u

WHERE NOT EXISTS (

&#x20;   SELECT 1

&#x20;   FROM Registrations r

&#x20;   WHERE r.user\_id = u.user\_id

&#x20;     AND r.registration\_date >= CURDATE() - INTERVAL 90 DAY

);

\--------------------------------------------------------------------------------------------------------------------------

##### **4. Peak Session Hours**



SELECT e.title,

&#x20;      COUNT(s.session\_id) AS session\_count

FROM Events e

LEFT JOIN Sessions s

ON e.event\_id = s.event\_id

AND TIME(s.start\_time) BETWEEN '10:00:00' AND '12:00:00'

GROUP BY e.event\_id, e.title;



\--------------------------------------------------------------------------------------------------------------------------

##### **5. Most Active Cities**

SELECT e.city,

&#x20;      COUNT(DISTINCT r.registration\_id) AS registrations

FROM Registrations r

JOIN Events e ON r.event\_id = e.event\_id

GROUP BY e.city

ORDER BY registrations DESC

LIMIT 5;

\--------------------------------------------------------------------------------------------------------------------------

##### **6. Event Resource Summary**

SELECT e.title,

&#x20;      COUNT(r.resource\_id) AS total\_resources

FROM Events e

LEFT JOIN Resources r

ON e.event\_id = r.event\_id

GROUP BY e.event\_id, e.title;

\--------------------------------------------------------------------------------------------------------------------------

##### **7. Low Feedback Alerts**

SELECT u.full\_name,

&#x20;      e.title,

&#x20;      f.rating,

&#x20;      f.comments

FROM Feedback f

JOIN Users u ON f.user\_id = u.user\_id

JOIN Events e ON f.event\_id = e.event\_id

WHERE f.rating < 3;

\--------------------------------------------------------------------------------------------------------------------------

##### **8. Sessions per Upcoming Event**

SELECT e.title,

&#x20;      COUNT(s.session\_id) AS session\_count

FROM Events e

LEFT JOIN Sessions s

ON e.event\_id = s.event\_id

WHERE e.status = 'upcoming'

GROUP BY e.event\_id, e.title;

\--------------------------------------------------------------------------------------------------------------------------

##### **9. Organizer Event Summary**

SELECT u.full\_name,

&#x20;      e.status,

&#x20;      COUNT(\*) AS event\_count

FROM Users u

JOIN Events e

ON u.user\_id = e.organizer\_id

GROUP BY u.full\_name, e.status;

\--------------------------------------------------------------------------------------------------------------------------

##### **10. Feedback Gap**

SELECT e.event\_id,

&#x20;      e.title

FROM Events e

JOIN Registrations r

ON e.event\_id = r.event\_id

LEFT JOIN Feedback f

ON e.event\_id = f.event\_id

GROUP BY e.event\_id, e.title

HAVING COUNT(f.feedback\_id) = 0;

\--------------------------------------------------------------------------------------------------------------------------

##### **11. Daily New User Count**

SELECT registration\_date,

&#x20;      COUNT(\*) AS user\_count

FROM Users

WHERE registration\_date >= CURDATE() - INTERVAL 7 DAY

GROUP BY registration\_date;

\--------------------------------------------------------------------------------------------------------------------------

##### **12. Event with Maximum Sessions**

SELECT e.title,

&#x20;      COUNT(s.session\_id) AS total\_sessions

FROM Events e

JOIN Sessions s

ON e.event\_id = s.event\_id

GROUP BY e.event\_id, e.title

HAVING COUNT(s.session\_id) = (

&#x20;   SELECT MAX(session\_count)

&#x20;   FROM (

&#x20;       SELECT COUNT(\*) AS session\_count

&#x20;       FROM Sessions

&#x20;       GROUP BY event\_id

&#x20;   ) t

);

\--------------------------------------------------------------------------------------------------------------------------

##### **13. Average Rating per City**

SELECT e.city,

&#x20;      AVG(f.rating) AS avg\_rating

FROM Events e

JOIN Feedback f

ON e.event\_id = f.event\_id

GROUP BY e.city;

\--------------------------------------------------------------------------------------------------------------------------

##### **14. Most Registered Events**

SELECT e.title,

&#x20;      COUNT(r.registration\_id) AS total\_registrations

FROM Events e

JOIN Registrations r

ON e.event\_id = r.event\_id

GROUP BY e.event\_id, e.title

ORDER BY total\_registrations DESC

LIMIT 3;

\--------------------------------------------------------------------------------------------------------------------------



##### **15. Event Session Time Conflict**



SELECT s1.event\_id,

&#x20;      s1.title AS session1,

&#x20;      s2.title AS session2

FROM Sessions s1

JOIN Sessions s2

ON s1.event\_id = s2.event\_id

AND s1.session\_id < s2.session\_id

AND s1.start\_time < s2.end\_time

AND s1.end\_time > s2.start\_time;

\--------------------------------------------------------------------------------------------------------------------------

##### **16. Unregistered Active Users**

SELECT \*

FROM Users u

WHERE u.registration\_date >= CURDATE() - INTERVAL 30 DAY

AND NOT EXISTS (

&#x20;   SELECT 1

&#x20;   FROM Registrations r

&#x20;   WHERE r.user\_id = u.user\_id

);

\--------------------------------------------------------------------------------------------------------------------------

##### **17. Multi-Session Speakers**

SELECT speaker\_name,

&#x20;      COUNT(\*) AS session\_count

FROM Sessions

GROUP BY speaker\_name

HAVING COUNT(\*) > 1;

\--------------------------------------------------------------------------------------------------------------------------

##### **18. Resource Availability Check**

SELECT e.title

FROM Events e

LEFT JOIN Resources r

ON e.event\_id = r.event\_id

WHERE r.resource\_id IS NULL;

\--------------------------------------------------------------------------------------------------------------------------

##### **19. Completed Events with Feedback Summary**

SELECT e.title,

&#x20;      COUNT(DISTINCT r.registration\_id) AS total\_registrations,

&#x20;      AVG(f.rating) AS avg\_rating

FROM Events e

LEFT JOIN Registrations r

ON e.event\_id = r.event\_id

LEFT JOIN Feedback f

ON e.event\_id = f.event\_id

WHERE e.status = 'completed'

GROUP BY e.event\_id, e.title;

\--------------------------------------------------------------------------------------------------------------------------

##### **20. User Engagement Index**

SELECT u.full\_name,

&#x20;      COUNT(DISTINCT r.event\_id) AS events\_attended,

&#x20;      COUNT(DISTINCT f.feedback\_id) AS feedbacks\_submitted

FROM Users u

LEFT JOIN Registrations r

ON u.user\_id = r.user\_id

LEFT JOIN Feedback f

ON u.user\_id = f.user\_id

GROUP BY u.user\_id, u.full\_name;

\--------------------------------------------------------------------------------------------------------------------------

##### **21. Top Feedback Providers**

SELECT u.full\_name,

&#x20;      COUNT(f.feedback\_id) AS feedback\_count

FROM Users u

JOIN Feedback f

ON u.user\_id = f.user\_id

GROUP BY u.user\_id, u.full\_name

ORDER BY feedback\_count DESC

LIMIT 5;

\--------------------------------------------------------------------------------------------------------------------------

##### **22. Duplicate Registrations Check**

SELECT user\_id,

&#x20;      event\_id,

&#x20;      COUNT(\*) AS duplicate\_count

FROM Registrations

GROUP BY user\_id, event\_id

HAVING COUNT(\*) > 1;

\--------------------------------------------------------------------------------------------------------------------------

##### **23. Registration Trends**

SELECT DATE\_FORMAT(registration\_date,'%Y-%m') AS month,

&#x20;      COUNT(\*) AS registrations

FROM Registrations

WHERE registration\_date >= CURDATE() - INTERVAL 12 MONTH

GROUP BY DATE\_FORMAT(registration\_date,'%Y-%m')

ORDER BY month;

\--------------------------------------------------------------------------------------------------------------------------

##### **24. Average Session Duration per Event**

SELECT e.title,

&#x20;      AVG(TIMESTAMPDIFF(MINUTE,

&#x20;                        s.start\_time,

&#x20;                        s.end\_time)) AS avg\_duration\_minutes

FROM Events e

JOIN Sessions s

ON e.event\_id = s.event\_id

GROUP BY e.event\_id, e.title;

\--------------------------------------------------------------------------------------------------------------------------

##### **25. Events Without Sessions**

SELECT e.title

FROM Events e

LEFT JOIN Sessions s

ON e.event\_id = s.event\_id

WHERE s.session\_id IS NULL;



