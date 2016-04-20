# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'latin1' */;

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (1,NULL,'Home','/basic/UserAction_home.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (2,NULL,'Logoff','/basic/LogonAction_logoff.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (3,NULL,'Admin',NULL,'T','F','F');
                                                           
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (4,3,'User','/basic/UserAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (5,3,'User add','/basic/UserAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (6,3,'User save','/basic/UserAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (7,3,'User delete','/basic/UserAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (8,3,'User detail','/basic/UserAction_detail.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (9,3,'User edit','/basic/UserAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (10,3,'User update','/basic/UserAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (11,3,'Change Password','/basic/UserAction_changePasswordEdit.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (12,3,'Change Password Confirm','/basic/UserAction_changePasswordConfirm.action','F','F','F');
                                                           
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (21,3,'Role','/basic/RoleAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (22,3,'Role add','/basic/RoleAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (23,3,'Role save','/basic/RoleAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (24,3,'Role delete','/basic/RoleAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (25,3,'Role detail','/basic/RoleAction_detail.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (26,3,'Role edit','/basic/RoleAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (27,3,'Role update','/basic/RoleAction_update.action','F','F','F');
                                                           
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (31,3,'Permission','/basic/PermissionAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (35,3,'Permission detail','/basic/PermissionAction_detail.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (36,3,'Permission edit','/basic/PermissionAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (37,3,'Permission update','/basic/PermissionAction_update.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (51,3,'Organization','/basic/OrganizationAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (52,3,'Organization add','/basic/OrganizationAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (53,3,'Organization save','/basic/OrganizationAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (54,3,'Organization delete','/basic/OrganizationAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (55,3,'Organization detail','/basic/OrganizationAction_detail.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (56,3,'Organization edit','/basic/OrganizationAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (57,3,'Organization update','/basic/OrganizationAction_update.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (71,3,'Lookup','/basic/LookupAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (72,3,'Lookup add','/basic/LookupAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (73,3,'Lookup save','/basic/LookupAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (74,3,'Lookup edit','/basic/LookupAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (75,3,'Lookup update','/basic/LookupAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (76,3,'Lookup delete','/basic/LookupAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (77,3,'Lookup detail','/basic/LookupAction_detail.action','F','F','F');
                                                           
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (91,3,'Status','/basic/StatusAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (92,3,'Status add','/basic/StatusAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (93,3,'Status save','/basic/StatusAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (94,3,'Status edit','/basic/StatusAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (95,3,'Status update','/basic/StatusAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (96,3,'Status delete','/basic/StatusAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (97,3,'Status detail','/basic/StatusAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (111,3,'User Activity','/basic/UserActivityAction_partialList.action','T','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (162,3,'Application Setup','/basic/ApplicationSetupAction_add.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (163,3,'Application Setup save','/basic/ApplicationSetupAction_save.action','F','F','F');                                                        

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (181,3,'Work Off Day','/basic/WorkOffDayAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (182,3,'Work Off Day add','/basic/WorkOffDayAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (183,3,'Work Off Day save','/basic/WorkOffDayAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (184,3,'Work Off Day edit','/basic/WorkOffDayAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (185,3,'Work Off Day update','/basic/WorkOffDayAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (186,3,'Work Off Day delete','/basic/WorkOffDayAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (187,3,'Work Off Day detail','/basic/WorkOffDayAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (201,3,'User Delegation','/basic/UserDelegationAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (202,3,'User Delegation add','/basic/UserDelegationAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (203,3,'User Delegation save','/basic/UserDelegationAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (204,3,'User Delegation edit','/basic/UserDelegationAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (205,3,'User Delegation update','/basic/UserDelegationAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (206,3,'User Delegation delete','/basic/UserDelegationAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (207,3,'User Delegation detail','/basic/UserDelegationAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (221,3,'Office/Branch','/basic/BranchAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (222,3,'Office/Branch add','/basic/BranchAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (223,3,'Office/Branch save','/basic/BranchAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (224,3,'Office/Branch edit','/basic/BranchAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (225,3,'Office/Branch update','/basic/BranchAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (226,3,'Office/Branch delete','/basic/BranchAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (227,3,'Office/Branch detail','/basic/BranchAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (10000,NULL,'Notification',NULL,'T','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (10010,10000,'Outgoing Email','/message/OutgoingEmailAction_partialList.action','T','F','F');


INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20000,NULL,'Workflow',NULL,'T','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20001,20000,'Workflow','/workflow/WorkflowAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20002,20000,'Workflow add','/workflow/WorkflowAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20003,20000,'Workflow save','/workflow/WorkflowAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20004,20000,'Workflow edit','/workflow/WorkflowAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20005,20000,'Workflow update','/workflow/WorkflowAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20006,20000,'Workflow delete','/workflow/WorkflowAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20007,20000,'Workflow detail','/workflow/WorkflowAction_detail.action','F','F','F');
 
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20021,20000,'Process','/workflow/ProcessAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20022,20000,'Process add','/workflow/ProcessAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20023,20000,'Process save','/workflow/ProcessAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20024,20000,'Process edit','/workflow/ProcessAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20025,20000,'Process update','/workflow/ProcessAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20026,20000,'Process delete','/workflow/ProcessAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (20027,20000,'Process detail','/workflow/ProcessAction_detail.action','F','F','F');


INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30000,NULL,'HR',NULL,'T','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30011,30000,'Attendance Machine','/hr/AttendanceMachineAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30012,30000,'Attendance Machine add','/hr/AttendanceMachineAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30013,30000,'Attendance Machine save','/hr/AttendanceMachineAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30014,30000,'Attendance Machine edit','/hr/AttendanceMachineAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30015,30000,'Attendance Machine update','/hr/AttendanceMachineAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30016,30000,'Attendance Machine delete','/hr/AttendanceMachineAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30017,30000,'Attendance Machine detail','/hr/AttendanceMachineAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30031,30000,'Grade','/hr/GradeAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30032,30000,'Grade add','/hr/GradeAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30033,30000,'Grade save','/hr/GradeAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30034,30000,'Grade edit','/hr/GradeAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30035,30000,'Grade update','/hr/GradeAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30036,30000,'Grade delete','/hr/GradeAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30037,30000,'Grade detail','/hr/GradeAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30051,30000,'Position','/hr/PositionAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30052,30000,'Position add','/hr/PositionAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30053,30000,'Position save','/hr/PositionAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30054,30000,'Position edit','/hr/PositionAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30055,30000,'Position update','/hr/PositionAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30056,30000,'Position delete','/hr/PositionAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30057,30000,'Position detail','/hr/PositionAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30071,30000,'Working Shift','/hr/WorkingShiftAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30072,30000,'Working Shift add','/hr/WorkingShiftAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30073,30000,'Working Shift save','/hr/WorkingShiftAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30074,30000,'Working Shift edit','/hr/WorkingShiftAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30075,30000,'Working Shift update','/hr/WorkingShiftAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30076,30000,'Working Shift delete','/hr/WorkingShiftAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30077,30000,'Working Shift detail','/hr/WorkingShiftAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30091,30000,'Employee','/hr/EmployeeAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30092,30000,'Employee add','/hr/EmployeeAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30093,30000,'Employee save','/hr/EmployeeAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30094,30000,'Employee edit','/hr/EmployeeAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30095,30000,'Employee update','/hr/EmployeeAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30096,30000,'Employee delete','/hr/EmployeeAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30097,30000,'Employee detail','/hr/EmployeeAction_detail.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30098,30000,'Employee import','/hr/EmployeeImportAction_importData.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30111,30000,'Employee Attendance','/hr/EmployeeAttendanceAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30112,30000,'Employee Attendance add','/hr/EmployeeAttendanceAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30113,30000,'Employee Attendance save','/hr/EmployeeAttendanceAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30114,30000,'Employee Attendance edit','/hr/EmployeeAttendanceAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30115,30000,'Employee Attendance update','/hr/EmployeeAttendanceAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30116,30000,'Employee Attendance delete','/hr/EmployeeAttendanceAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30117,30000,'Employee Attendance detail','/hr/EmployeeAttendanceAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30121,30000,'Upload File Mesin Absen','/hr/EmployeeAttendanceUploadAction_add.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30122,30000,'Employee Attendance upload save','/hr/EmployeeAttendanceUploadAction_save.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30131,30000,'Employee Leave','/hr/EmployeeLeaveAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30134,30000,'Employee Leave edit','/hr/EmployeeLeaveAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30135,30000,'Employee Leave update','/hr/EmployeeLeaveAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30137,30000,'Employee Leave detail','/hr/EmployeeLeaveAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30151,30000,'Employee Loan','/hr/EmployeeLoanAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30152,30000,'Employee Loan add','/hr/EmployeeLoanAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30153,30000,'Employee Loan save','/hr/EmployeeLoanAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30154,30000,'Employee Loan edit','/hr/EmployeeLoanAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30155,30000,'Employee Loan update','/hr/EmployeeLoanAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30156,30000,'Employee Loan delete','/hr/EmployeeLoanAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30157,30000,'Employee Loan detail','/hr/EmployeeLoanAction_detail.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30158,30000,'Employee Loan Terminate','/hr/EmployeeLoanAction_terminateAdd.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30159,30000,'Employee Loan Terminate save','/hr/EmployeeLoanAction_terminateSave.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30171,30000,'Employee Out of Town Duty','/hr/EmployeeOutOfTownDutyAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30172,30000,'Employee Out of Town Duty add','/hr/EmployeeOutOfTownDutyAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30173,30000,'Employee Out of Town Duty save','/hr/EmployeeOutOfTownDutyAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30174,30000,'Employee Out of Town Duty edit','/hr/EmployeeOutOfTownDutyAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30175,30000,'Employee Out of Town Duty update','/hr/EmployeeOutOfTownDutyAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30176,30000,'Employee Out of Town Duty delete','/hr/EmployeeOutOfTownDutyAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30177,30000,'Employee Out of Town Duty detail','/hr/EmployeeOutOfTownDutyAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30191,30000,'Employee Out of Town Duty','/hr/EmployeeOutOfTownDutyAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30192,30000,'Employee Out of Town Duty add','/hr/EmployeeOutOfTownDutyAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30193,30000,'Employee Out of Town Duty save','/hr/EmployeeOutOfTownDutyAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30194,30000,'Employee Out of Town Duty edit','/hr/EmployeeOutOfTownDutyAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30195,30000,'Employee Out of Town Duty update','/hr/EmployeeOutOfTownDutyAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30196,30000,'Employee Out of Town Duty delete','/hr/EmployeeOutOfTownDutyAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30197,30000,'Employee Out of Town Duty detail','/hr/EmployeeOutOfTownDutyAction_detail.action','F','F','F');


INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30211,30000,'Echelon','/hr/EchelonAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30212,30000,'Echelon add','/hr/EchelonAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30213,30000,'Echelon save','/hr/EchelonAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30214,30000,'Echelon edit','/hr/EchelonAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30215,30000,'Echelon update','/hr/EchelonAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30216,30000,'Echelon delete','/hr/EchelonAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30217,30000,'Echelon detail','/hr/EchelonAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30251,30000,'Leave Deduction','/hr/LeaveDeductionAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30252,30000,'Leave Deduction add','/hr/LeaveDeductionAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30253,30000,'Leave Deduction save','/hr/LeaveDeductionAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30254,30000,'Leave Deduction edit','/hr/LeaveDeductionAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30255,30000,'Leave Deduction update','/hr/LeaveDeductionAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30256,30000,'Leave Deduction delete','/hr/LeaveDeductionAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30257,30000,'Leave Deduction detail','/hr/LeaveDeductionAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30271,30000,'Employee Attendance Allowance Monthly','/hr/EmployeeAttendanceAllowanceMonthlyAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30277,30000,'Employee Attendance Allowance Monthly detail','/hr/EmployeeAttendanceAllowanceMonthlyAction_detail.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30278,30000,'Delete Employee Attendance Allowance Monthly','/hr/EmployeeAttendanceAllowanceMonthlyAction_deleteForm.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30279,30000,'Employee Attendance Allowance Monthly Delete Confirm','/hr/EmployeeAttendanceAllowanceMonthlyAction_deleteConfirm.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30292,30000,'Request Employee Attendance Allowance Monthly','/hr/EmployeeAttendanceAllowanceMonthlyReqAction_add.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30293,30000,'Employee Attendance Allowance Monthly Request save','/hr/EmployeeAttendanceAllowanceMonthlyReqAction_save.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30311,30000,'Rating','/hr/RatingAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30312,30000,'Rating add','/hr/RatingAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30313,30000,'Rating save','/hr/RatingAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30314,30000,'Rating edit','/hr/RatingAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30315,30000,'Rating update','/hr/RatingAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30316,30000,'Rating delete','/hr/RatingAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30317,30000,'Rating detail','/hr/RatingAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30331,30000,'Degree','/hr/DegreeAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30332,30000,'Degree add','/hr/DegreeAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30333,30000,'Degree save','/hr/DegreeAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30334,30000,'Degree edit','/hr/DegreeAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30335,30000,'Degree update','/hr/DegreeAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30336,30000,'Degree delete','/hr/DegreeAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30337,30000,'Degree detail','/hr/DegreeAction_detail.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30351,30000,'Bendahara Satuan Kerja','/hr/BranchGroupAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30352,30000,'Bendahara Satuan Kerja tambah','/hr/BranchGroupAction_add.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30353,30000,'Bendahara Satuan Kerja simpan','/hr/BranchGroupAction_save.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30354,30000,'Bendahara Satuan Kerja ubah','/hr/BranchGroupAction_edit.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30355,30000,'Bendahara Satuan Kerja perbaharui','/hr/BranchGroupAction_update.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30356,30000,'Bendahara Satuan Kerja hapus','/hr/BranchGroupAction_delete.action','F','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (30357,30000,'Bendahara Satuan Kerja rincian','/hr/BranchGroupAction_detail.action','F','F','F');



INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32001,30000,'Employee Task List','/hr/EmployeeTaskListAction_list.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32011,30000,'Employee Leave process start','/hr/EmployeeLeaveProcessAction_start.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32012,30000,'New Employee Leave','/hr/EmployeeLeaveFlowAction_add.action','T','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32013,30000,'Employee Leave save','/hr/EmployeeLeaveFlowAction_save.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32014,30000,'Employee Leave Approve Form','/hr/EmployeeLeaveFlowAction_approveForm.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32015,30000,'Employee Leave Approve Confirm','/hr/EmployeeLeaveFlowAction_approveConfirm.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32016,30000,'Employee Leave Approve Next Approval','/hr/EmployeeLeaveFlowAction_nextUserApproval.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32017,30000,'Employee Leave Doc File add','/hr/EmployeeLeaveFlowAction_documentFileAdd.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32018,30000,'Employee Leave Doc File save','/hr/EmployeeLeaveFlowAction_documentFileSave.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32019,30000,'Employee Leave Close','/hr/EmployeeLeaveFlowAction_close.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32020,30000,'Employee Leave Select Tata Usaha','/hr/EmployeeLeaveFlowAction_nextUserTataUsaha.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32091,30000,'Employee Leave add note','/hr/EmployeeLeaveProcessAction_addNote.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32092,30000,'Employee Leave save note','/hr/EmployeeLeaveProcessAction_saveNote.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32099,30000,'Employee Leave process end','/hr/EmployeeLeaveProcessAction_end.action','F','T','F');


INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32111,30000,'Employee Attendance Allowance Monthly process start','/hr/EmployeeAttendanceAllowanceMonthlyProcessAction_start.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32112,30000,'New Employee Attendance Allowance Monthly','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_add.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32113,30000,'Employee Attendance Allowance Monthly save','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_save.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32114,30000,'Employee Attendance Allowance Monthly Approve Form','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_approveForm.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32115,30000,'Employee Attendance Allowance Monthly Approve Confirm','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_approveConfirm.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32116,30000,'Employee Attendance Allowance Monthly Next Approval','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_nextUserApproval.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32118,30000,'Employee Attendance Allowance Monthly Select Tata Usaha','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_nextUserTataUsaha.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32119,30000,'Employee Attendance Allowance Monthly Close Form','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_closeForm.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32120,30000,'Employee Attendance Allowance Monthly Close Confirm','/hr/EmployeeAttendanceAllowanceMonthlyFlowAction_closeConfirm.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32191,30000,'Employee Attendance Allowance Monthly add note','/hr/EmployeeAttendanceAllowanceMonthlyProcessAction_addNote.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32192,30000,'Employee Attendance Allowance Monthly save note','/hr/EmployeeAttendanceAllowanceMonthlyProcessAction_saveNote.action','F','T','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (32199,30000,'Employee Attendance Allowance Monthly process end','/hr/EmployeeAttendanceAllowanceMonthlyProcessAction_end.action','F','T','F');



INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90000,NULL,'Report',NULL,'T','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90011,90000,'Rekap Tunjangan Hadir','/report/AttendanceAllowanceRekapitulationReportAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90012,90000,'Rekap Tunjangan Hadir XLS','/report/AttendanceAllowanceRekapitulationReportAction_excelList.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90021,90000,'Rekap Tunjangan Hadir Karyawan','/report/EmployeeAttendanceAllowanceRekapitulationReportAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90022,90000,'Rekap Tunjangan Hadir Karyawan XLS','/report/EmployeeAttendanceAllowanceRekapitulationReportAction_excelList.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90031,90000,'Tunjangan Hadir Karyawan','/report/EmployeeAttendanceAllowanceReportAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90032,90000,'Tunjangan Hadir Karyawan XLS','/report/EmployeeAttendanceAllowanceReportAction_excelList.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90041,90000,'Pembayaran Tunjangan Hadir','/report/EmployeeAttendanceAllowancePaymentReportAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90042,90000,'Pembayaran Tunjangan Hadir XLS','/report/EmployeeAttendanceAllowancePaymentReportAction_excelList.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90051,90000,'Tanda Terima Pembayaran Tunjangan Hadir','/report/EmployeeAttendanceAllowancePaymentSignReportAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90052,90000,'Tanda Terima Pembayaran Tunjangan Hadir XLS','/report/EmployeeAttendanceAllowancePaymentSignReportAction_excelList.action','F','F','F');

INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90061,90000,'Kalendar Kehadiran Karyawan','/report/EmployeeAttendanceCalendarReportAction_partialList.action','T','F','F');
INSERT INTO permission (permission_id,parent_id,permission_name,link,is_show,is_workflow,is_log) VALUES (90062,90000,'Kalendar Kehadiran Karyawan XLS','/report/EmployeeAttendanceCalendarReportAction_excelList.action','F','F','F');


