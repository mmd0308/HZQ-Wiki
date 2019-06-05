  select
            s.id,
            s.name,
            s.space_id,
            s.remark,
            s.visit_level,
            s.create_by,
            s.create_time,
            s.update_by,
            s.update_time,
            u.privilege
        from
            hw_doc s
        LEFT JOIN
            hw_user_doc u ON u.doc_id = s.id
        where
            s.create_by = 5
            or find_in_set(s.id, (select doc_id from hw_user_doc where user_id = 5))
            or s.visit_level = 1