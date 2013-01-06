Ipms.Action = new function () {
};
Ipms.Action.doAction = function (e, t) {
    e.stopEvent();
    var actionName = t.id.replace('MenuBarItem-a-', '');

    if (!Ipms.Action.actions[actionName]) {
        alert('对不起，该功能尚未实现。');
        return;
    }

    Ipms.Action.actions[actionName]();
};
Ipms.Action.actions = {
    'project-vertical-new': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.newProject(false);');
    },
    'project-vertical-list': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listVerticalProject(false);');
    },
    'project-vertical-query': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listVerticalProject(true);');
    },
    'project-vertical-censor-start': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingStartCensorVerticalProject (false);');
    },
    'project-vertical-censor-end': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingEndCensorVerticalProject (false);');
    },
    'project-vertical-censor-document': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingCenorVerticalProjecctDocuments ();');
    },
    'project-vertical-censor-contract': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingCenorVerticalProjecctContracts ();');
    },
    'project-vertical-censor-fund-allocation': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listWaitingCensorFundAllocation_Vertical();');
    },
    'project-horizontal-censor-start': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingStartCensorHorizontalProject (false);');
    },
    'project-horizontal-censor-end': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingEndCensorHorizontalProject (false);');
    },
    'project-horizontal-new': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.newProject(true);');
    },
    'project-horizontal-list': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listHorizontalProject(false);');
    },
    'project-horizontal-query': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listHorizontalProject(true);');
    },
    'project-horizontal-censor-document': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingCenorHorizontalProjecctDocuments();');
    },
    'project-horizontal-censor-contract': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listWaitingCenorHorizontalProjecctContracts();');
    },
    'project-horizontal-censor-fund-descend': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listWaitingCensorFundDescend_Horizontal() ;');
    },
    'project-horizontal-censor-fund-allocation': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listWaitingCensorFundAllocation_Horizontal();');
    },
    'project-waiting-set-delegate': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.showWaitingSetDelegateWindow()');
    },
    'expert-simple-query': function () {
        Ipms.Load.loadExpertSimpleQuery('Ipms.experts.ExpertSimpleQuery.window.show();');
    },
    'project-my-project-principal': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listMyPrincipalProject();');
    },
    'project-my-project-join': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listMyParticipateProject();');
    },
    'project-my-project-delegate': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listMyDelegateProject();');
    },
    'expert-my-unsubmit-document': function () {
        Ipms.Load.loadProjectModule('Ipms.projects.listMyUnsubmitDocument()');
    },
    'announcement-list': function () {
        Ipms.Load.loadAnnouncementModule('Ipms.common.AnnouncementAction.listAnnouncement(false);');
    },
    'announcement-new': function () {
        Ipms.Load.loadAnnouncementModule('Ipms.common.AnnouncementAction.listAnnouncement(true);');
    },
    'subject-level-first': function () {
        Ipms.Load.loadSubjectModule('Ipms.common.listSubjectFirstLevel();');
    },
    'subject-level-second': function () {
        Ipms.Load.loadSubjectModule('Ipms.common.listSubjectSecondLevel();');
    },
    'user-list': function () {
        Ipms.Load.loadUserModule('Ipms.users.UserAction.listUser(false);');
    },
    'user-new': function () {
        Ipms.Load.loadUserModule('Ipms.users.UserAction.listUser(true);');
    },
    'expert-list': function () {
        Ipms.Load.loadExpertModule('Ipms.experts.ExpertAction.listExpert(false);')
    },
    'expert-query': function () {
        Ipms.Load.loadExpertModule('Ipms.experts.ExpertAction.listExpert(true);')
    },
    'expert-new': function () {
        Ipms.Load.loadExpertModule('Ipms.experts.ExpertAction.newExpert();')
    },
    'expert-edit-censor': function () {
        Ipms.Load.loadExpertModule('Ipms.experts.ExpertAction.showWaitingCensorExpertInfoHistories();')
    },
    'activeuser-list': function () {
        Ipms.Load.loadUserModule('Ipms.users.UserAction.listActiveUsers();');
    },
    'notoiceText-manage': function () {
        Ipms.Load.loadNoticeTextModule('Ipms.common.NoticeTextShowForm();');
    },
    'message-list': function () {
        Ipms.Load.loadMessageModule('Ipms.users.MessageAction.listMyMessages()');
    },
    'paper-new': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.newPaper()');
    },
    'paper-list': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.listPaper(false)');
    },
    'paper-query': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.listPaper(true)');
    },
    'expert-my-paper': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.listPaper(false)');
    },
    'magazine-new': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.newMagazine()');
    },
    'magazine-list': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.listMagazine(false)');
    },
    'magazine-query': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.listMagazine(true)');
    },
    'patent-list': function () {
        Ipms.Load.loadPatentModule('Ipms.patents.listPatent(false,false);');
    },
    'patent-query': function () {
        Ipms.Load.loadPatentModule('Ipms.patents.listPatent(true,false);');
    },
    'patent-new': function () {
        Ipms.Load.loadPatentModule('Ipms.patents.listPatent(false,true);');
    },
    'patent-agency-manage': function () {
        Ipms.Load.loadPatentModule('Ipms.patents.listPatentAgency();');
    },
    'expert-my-patent': function () {
        Ipms.Load.loadPatentModule('Ipms.patents.listPatent(false,false);');
    },
    'award-list': function () {
        Ipms.Load.loadAwardModule('Ipms.awards.listAward(false,false);');
    },
    'award-query': function () {
        Ipms.Load.loadAwardModule('Ipms.awards.listAward(true,false);');
    },
    'award-new': function () {
        Ipms.Load.loadAwardModule('Ipms.awards.listAward(false,true);');
    },
    'award-waiting-censor-document': function () {
        Ipms.Load.loadAwardModule('Ipms.awards.listWaitingCensorDocument();');
    },
    'award-expert-match': function () {
        Ipms.Load.loadAwardModule('Ipms.awards.listAward(false,true);');
    },
    'expert-my-award': function () {
        Ipms.Load.loadAwardModule('Ipms.awards.listAward(false,false);');
    },
    'log-manage': function () {
        Ipms.Load.loadLogModel('Ipms.common.listLog();');
    },
    'statistic-project-count': function () {
        Ipms.Load.loadStatisticModule('Ipms.common.showViewWindow("ProjectCountStatic");');
    },
    'statistic-fund-total': function () {
        Ipms.Load.loadStatisticModule('Ipms.common.showViewWindow("ProjectFundTotalStatic");');
    },
    'statistic-fund-descend': function () {
        Ipms.Load.loadStatisticModule('Ipms.common.showViewWindow("FundDescendStatic");');
    },
    'statistic-voucher': function () {
        Ipms.Load.loadStatisticModule('Ipms.common.showViewWindow("FundAllocationStatic");');
    },
    'statistic-paper': function () {
        Ipms.Load.loadStatisticModule('Ipms.common.showViewWindow("PaperStatic");');
    },
    'statistic-patent': function () {
        Ipms.Load.loadStatisticModule('Ipms.common.showViewWindow("PatentStatic");');
    },
    'statistic-award': function () {
        Ipms.Load.loadStatisticModule('Ipms.common.showViewWindow("AwardStatic");');
    },
    'system-setting': function () {
        Ipms.Load.LoadSystemSettingModel('Ipms.common.systemSettingWindows();');
    },
    'fund-finance': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listFinance()');
    },
    'fund-waiting-allocation-vertical-project': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listWaitingAlloactionFundDescend_vertical()');
    },
    'fund-waiting-allocation-horizontal-project': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listWaitingAlloactionFundDescend_Horizontal()');
    },
    'fund-lent': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listFundBorrow_UnCompleteReturn()');
    },
    'expert-fund-descend': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listMyDescendFund()');
    },
    'expert-fund-allocation': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listMyFundDesend()');
    },
    'finance-information': function () {
        Ipms.Load.loadFinanceModule('Ipms.finance.listFinance()');
    },
    'notice-text-manage': function () {
        Ipms.Load.loadNoticeTextModule('Ipms.common.noticeTextManage();')
    },
    'voucher-finance-state': function () {
        Ipms.Load.loadFinanceModule('Ipms.finance.listVoucherFinance()');
    },
    'fund-voucher': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listVoucher()');
    },
    'voucher-finance-financebak': function () {
        Ipms.Load.loadFinanceModule('Ipms.finance.listFinanceBak()');
    },
    'fund-allocation': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listFundAllcation()');
    },
    'department-manage': function () {
        Ipms.Load.loadDepartmentModel('Ipms.experts.listDepartment();');
    },
    'expert-my-voucher': function () {
        Ipms.Load.loadFundModule('Ipms.fund.listMyVoucher()');
    },
    'type-type': function () {
        Ipms.Load.loadTypeModel('Ipms.type.listProjectType()');
    },
    'type-managementfees': function () {
        Ipms.Load.loadTypeModel('Ipms.type.listManagementFees()');
    },
    'stamp-list': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.listStamps(false)');
    },
    'stamp-censor': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.listWaitingCensorStamps()');
    },
    'stamp-apply': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.newStampApplication()');
    },
    'waitingStamp-list': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.listWaitingStampStamps()');
    },
    'stampDepartment-censor': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.listWaitingDepartmentCensorStamps()');
    },
    'departmentStamp-list': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.listDepartmentStamps(false)');
    },
    'expert-stamp-apply': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.newStampApplication()');
    },
    'expert-my-stamp': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.listMyStamps()');
    },
    'stamp-manage': function () {
        Ipms.Load.loadStampModel('Ipms.stamp.showStampManageWindow()');
    },
    'expert-guid-fund-descend': function () {
        Ipms.Load.loadFundModule('Ipms.expertGuide.showFundDescendProcessPanel()');
    },
    'expert-guid-fund-allocation': function () {
        Ipms.Load.loadFundModule('Ipms.expertGuide.showFundAllocationProcessPanel()');
    },
    'achieve-view': function () {
        Ipms.Load.loadAchieveViewModule('Ipms.experts.myAchieveView()');
    },
    'help': function () {
        Ipms.Load.loadHelpModule('Ipms.experts.getHelp()');
    },
    'change-password': function () {
        Ipms.Load.loadChangePasswordModule('Ipms.users.changePassword()');
    },
    'magazine-Occupation': function () {
        Ipms.Load.loadPaperModule('Ipms.papers.listMagazineOccupation()');
    },
    'base-new': function () {
        Ipms.Load.loadBaseModule('Ipms.bases.listBase(true)');
    },
    'base-list': function () {
        Ipms.Load.loadBaseModule('Ipms.bases.listBase(false)');
    },
    'outsourcing-manage': function () {
        Ipms.Load.loadOutsourcingModule('Ipms.common.ListOutsourcing(false)');
    },
    'outsourcing-inquiry': function () {
        Ipms.Load.loadOutsourcingModule('Ipms.common.ListOutsourcing(true)');
    },
    'outsourcing-statistics': function () {
        Ipms.Load.loadOutsourcingModule('Ipms.common.StatisticsOutsourcing()');
    },
    'outsourcingunit-manage': function () {
        Ipms.Load.loadOutsourcingUnitModule('Ipms.common.ListOutsourcingUnit()');
    },
    'performance-manage': function () {
        Ipms.Load.loadperformanceModule('Ipms.common.ListPerformance()');
    }
};