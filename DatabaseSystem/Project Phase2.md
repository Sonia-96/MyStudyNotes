# Project Ph

```
DELIMITER $$
CREATE TRIGGER submission_score_check
BEFORE UPDATE OR INSERT ON submission
FOR EACH ROW
BEGIN
  DECLARE assignment_points SMALLINT UNSIGNED;
  SELECT points INTO assignment_points FROM assignments WHERE id = NEW.assignment_id;
  IF NEW.score IS NOT NULL AND NEW.score > assignment_points THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Score cannot exceed assignment points.';
  END IF;
END$$
DELIMITER ;
```