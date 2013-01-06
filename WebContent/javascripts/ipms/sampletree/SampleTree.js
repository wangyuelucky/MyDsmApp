Ext.BLANK_IMAGE_URL = 'images/default/s.gif';//(注意你本地extjs的路径)
Ext.onReady(function() {
    Ext.QuickTips.init();
	
	// 存放节点动作
	var nodeArray = new Array();
		
    // TreePanel
    var tree = new Ext.tree.TreePanel({
		id : 'project-device-tree',
		title : '项目设备树',
		root: {
			text :'项目设备树',
			id :'1',
			expanded : 'true',
			leaf: false
		},
		rootVisible : false,
		collapsed : false,
		region : 'west',
		animate : true,
		enableDD : true,
		containerScroll : true,
		dataUrl : 'treeNode.do?operator=showNode',
		listeners: {
		    // 禁止编辑
			beforeclick: function(a,b) { 
				if(this.getSelectionModel().isSelected(a)){
					return false;
				}
			}
		},
		bbar : [{
			iconCls : "wy-save-node",
			text : '提交', // 提交按钮
			handler : nodeChangesSubmit,
			scope : this
		}],
		renderTo: 'tree-div',
		height: 300,
        width: 250,
		lines : true,
		autoScroll : true,
		containerScroll : false,
		border : true
    });
	
	// 给tree添加右键
	tree.on('rightClickCont', tree.rightClick, tree);
		
	// 定义右键菜单
	var rightClick = new Ext.menu.Menu({
		id : 'rightClickCont',
		items : [{
					id : 'addNode',
					text : '添加',
					iconCls : "wy-add-node",
					handler : function(tree) {
						appendNodeAction(); }
				},  
				'-',
				{
					id : 'modifNode',
					text : '修改',
					iconCls : "wy-edit-node",
					handler : function() {
						modifNodeAction();}
				},
				'-', 
				{
					id : 'delNode',
					text : '删除',
					iconCls : "wy-delete-node",
					handler : function(tree) {
						delNodeAction();}
				}]
    });
	
	// 提交nodes
	function nodeChangesSubmit(){
		var result = "";
		for(var i=0;i<nodeArray.length;i++){
			result += nodeArray[i].act + "\n";
		}
		alert(result);
		// 清空数组
		nodeArray.splice(0);
	};
	
	// 鼠标单击事件(test)
//	tree.on('click', function(node) {
//		if (node.id != 'root') {
//			alert('text：' + node.text + ',id: ' + node.id + '');
//		}
//		if(node.attributes.projectId){
//			alert("当前设备所属的项目id："+node.attributes.projectId);
//		}
//		if(node.attributes.parentId){
//			alert("当前项目的父项目id："+node.attributes.parentId);
//		}
//		
//    });
	
	// 增加右键弹出事件
	tree.on('contextmenu', function(node, event) {
		event.preventDefault();// 防止浏览器的默认事件操作发生
		node.select();
		// 只有项目允许右键菜单操作
		if(node.attributes.pOd == "p"){
			rightClick.showAt(event.getXY());
		}
    });
	
	// 拖拽释放前的事件
	tree.on('beforemovenode', function(tree, node, oldParent, newParent, index) {
		alert("nodeid: "+node.id+" newparentid: "+newParent.id+" oldparentid: "+oldParent.id+" text: "+"");
		//var nodeMove = {act:"move",nodeid:node.id,newparentid:newParent.id,oldparentid:oldParent.id,text:""};
		//nodeArray.push(nodeMove);
		
		// “项目叶子”图标，变为“项目非叶子”
		newParent.leaf = false;
	});
	
     // 树形编辑器
	var treeEditor = new Ext.tree.TreeEditor(tree, {
		allowBlank:false,
		cancelOnEsc:true,
		completeOnEnter:true,
		selectOnFocus:true
	});
	
	// 编辑开始前的事件
	treeEditor.on('beforestartedit', function(treeEditor){
		var selectedNode = tree.getSelectionModel().getSelectedNode();
		var parentNode = selectedNode.parentNode;
		// 所有孩子
		var childArr = selectedNode.childNodes;
		var len = childArr.length;
		var match = false;
		for(var i=0; i<len;i++){
			if(childArr[i].attributes.state == "1"){ // 验证条件，设备被拒绝
				match = true;
				break;
			}
		}
		if(parentNode==null){ // 根节点不能被修改
			return false;
		}else if(match){
			Ext.Msg.alert("警告", "设备被审核，项目信息不能被修改!");
			return false;
		}else{
			return true;
		}
	});
	
	// 编辑完成前的事件
	treeEditor.on('complete', function(ed,newValue,oldValue){
		tree.getTreeEl().un("scroll",this.cancelEdit,this);
		treeEditor.editNode.setText(newValue); 
		var selectedNode = tree.getSelectionModel().getSelectedNode();
		var parentNode = selectedNode.parentNode;
		//alert("act:edit"+" nodeid: "+selectedNode.id+" newparentid:"+parentNode.id+" oldparentid:"+parentNode.id+"text:"+b);
		var _nodeEdit = {act:"edit",nodeid:selectedNode.id,newparentid:parentNode.id,oldparentid:parentNode.id,text:newValue};
		var nodeInfo = "nodeid:"+selectedNode.id+",text:"+newValue+",parentid:"+parentNode.id;
		var nodeId = selectedNode.id.toString();
		nodeArray.push(_nodeEdit);
		// 取消或者无修改的时候，不提交数据库
		// 新建，但是按ESC，提交数据库
		if(nodeId.indexOf("ynode")==0 ||newValue != oldValue){
			Ext.Ajax.request({  
                url : 'treeNode.do',  
                params : {operator : 'saveOrUpdate',nodeInfo:nodeInfo},  
                method : 'POST',  
                success : function(response) {  
		            if (response.responseText == "true") {
						Ext.Msg.alert("提示", "操作成功!");
					} else {
						Ext.Msg.alert("系统消息", "操作失败,请检重新操作或与管理员联系!");
					}
					tree.root.reload();
                },  
                failure : function() {  
                    Ext.Msg.alert("提示", "方法调用失败"); 
                    tree.root.reload();
                }  
            }); 
		}
	});
		
	// 添加子节点事件实现
	function appendNodeAction() {
		var selectedNode = tree.getSelectionModel().getSelectedNode();
		
		var newNode = selectedNode.appendChild(new Ext.tree.TreeNode({
			text : '新建子项目',
			iconCls : 'wy-projectLeafIcon'
		}));
		// 将上级树形展开
		newNode.parentNode.expand(true, true, function() {
			tree.getSelectionModel().select(newNode);
			setTimeout(function() {
				treeEditor.editNode = newNode;
				treeEditor.startEdit(newNode.ui.textNode);
			}, 10);
		});
	}	
	
	// 删除节点事件实现
	function delNodeAction() { 
		var selectedNode = tree.getSelectionModel().getSelectedNode();
		var parentNode = selectedNode.parentNode;
		// 所有孩子
		var childArr = selectedNode.childNodes;
		var len = childArr.length;
		var match = false;
		for(var i=0; i<len;i++){
			if(childArr[i].attributes.state == "1"){ // 验证条件，设备被拒绝
				match = true;
				break;
			}
		}
		if(selectedNode.attributes.parentId==parseInt("1")){
			Ext.MessageBox.alert("警告","项目根节点不能删除！");
		}else if(match){
			Ext.MessageBox.alert("警告","设备被审核，项目不能被删除！");
		}else{
			Ext.MessageBox.confirm(
				"刪除设备",
				"您確定要刪除「"+selectedNode.text+"」及其子项目吗？",
				function(btn){
					if(btn == 'yes'){
						//alert("act:del"+" nodeid: "+selectedNode.id+" newparentid:"+parentNode.id+" oldparentid:"+parentNode.id+"text:"+"");
						var nodeInfo = "nodeid:"+selectedNode.id+",parentid:"+parentNode.id;
						var nodeDel = {act:"del",nodeid:selectedNode.id,newparentid:parentNode.id,oldparentid:parentNode.id,text:""};
						nodeArray.push(nodeDel);
						selectedNode.remove();
						
						Ext.Ajax.request({  
               				url : 'treeNode.do',  
              			    params : {operator : 'deletePro',nodeInfo:nodeInfo},  
                			method : 'POST',  
                			success : function(response) {  
		            			if (response.responseText == "true") {
									Ext.Msg.alert("提示", "操作成功!");
								} else {
									Ext.Msg.alert("系统消息", "操作失败,请检重新操作或与管理员联系!");
								}
								tree.root.reload();
                			},  
                			failure : function() {  
                 			   Ext.Msg.alert("提示", "方法调用失败"); 
                   			 tree.root.reload();
                			}  
           			 });
					}
				},
				this
			);
		}
	};
	
	// 修改节点事件实现
	function modifNodeAction() {
		var selectedNode = tree.getSelectionModel().getSelectedNode();
		if(selectedNode.attributes.parentId==parseInt("1")){
			Ext.MessageBox.alert("警告","项目根节点不能编辑！");
		}else{
			treeEditor.editNode = selectedNode;
			treeEditor.startEdit(selectedNode.ui.textNode);
		}
		
	};
	
});
