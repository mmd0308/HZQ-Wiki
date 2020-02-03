use hzq_wiki;

-- -----------------------------------------------------
-- Table `hzq_wiki`.`hw_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hzq_wiki`.`hw_tag` ;

CREATE TABLE IF NOT EXISTS `hzq_wiki`.`hw_tag` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '标签名称',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '标签管理';


-- -----------------------------------------------------
-- Table `hzq_wiki`.`hw_article_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hzq_wiki`.`hw_article_tag` ;

CREATE TABLE IF NOT EXISTS `hzq_wiki`.`hw_article_tag` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `article_id` INT(10) NULL,
  `tag_id` INT(10) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '文章和标签管理关系表';


ALTER TABLE `hzq_wiki`.`hw_doc` 
CHANGE COLUMN `visit_level` `visit_level` INT(11) NULL DEFAULT NULL COMMENT '访问级别 1 私有的 2 公开的' ;

-- ---------- 文档旧数据更新
update  hw_doc  set visit_level = 2 where visit_level=1;

update  hw_doc  set visit_level = 1 where visit_level=0;


ALTER TABLE `hzq_wiki`.`hw_article` 
CHANGE COLUMN `hw_state` `hw_state` INT(11) NULL DEFAULT NULL COMMENT '状态 1 草稿 2 发布' ;



ALTER TABLE `hzq_wiki`.`hw_space` 
CHANGE COLUMN `visit_level` `visit_level` INT(11) NULL DEFAULT '1' COMMENT '访问级别 1 私有的 2 公开的 ' ;

update  hw_space  set visit_level = 2 where visit_level=1;

update  hw_space  set visit_level = 1 where visit_level=0;



ALTER TABLE `hzq_wiki`.`hw_user_doc` 
CHANGE COLUMN `privilege` `privilege` INT(10) NULL DEFAULT NULL COMMENT '空间成员操作权限   1 拥有者 2 浏览者 3 编辑者 4 管理员 \n\n' ;

update  hw_user_doc  set privilege =4 where privilege=3;

update  hw_user_doc  set privilege =3 where privilege=2;

update  hw_user_doc  set privilege =2 where privilege=1;

update  hw_user_doc  set privilege =1 where privilege=0;

