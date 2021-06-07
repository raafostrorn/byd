var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : -1
		},
		key : {
			url : "nourl"
		}
	}
};
var ztree;

$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'emotion/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'id', name: 'id', index: 'id', width: 50, key: true },*/
			{ label: '情感', name: 'emotionWord', index: 'emotion_word', width: 80 }, 			
			/*{ label: '维度id', name: 'dimensionId', index: 'dimension_id', width: 80 }, 	*/		
			{ label: '维度', name: 'dimensionName', index: 'dimension_name', width: 80 }, 			
			{ label: '极性值', name: 'worth', index: 'worth', width: 80 }, 			
			{ label: '故障', name: 'isfault', index: 'isfault', width: 80 }, 			
			{ label: '别名', name: 'emotionNickname', index: 'emotion_nickname', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		q:{
			emotionWord: '',
			dimensionId: '',
			dimensionName: '',
			worth: '',
        },
//		dimension : {
//			parentId : 0,
//		},
		emotion: {}
	},
	methods: {
		query: function () {
            $("#jqGrid").setGridParam({'page': 1});
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.emotion = {};
			vm.emotion = {
				dimensionId : 0
			};
			vm.getMenu();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		downexcel:function(){
			location.href = baseURL + "emotion/export?token=" + token;
		},
		saveOrUpdate: function (event) {
			if (vm.validator()) {
				return;
			}
			var url = vm.emotion.id == null ? "emotion/save" : "emotion/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.emotion),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "emotion/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "emotion/info/"+id, function(r){
                vm.emotion = r.emotion;
				vm.getMenu();
            });
		},
		getMenu : function() {
			//加载菜单树
			$.get(baseURL + "dimension/list/null/0", function(r) {
				//添加顶级菜单
				r.push({
					id : 0,
					name : "选择维度",
					parentId : -1,
					open : true
				});
				ztree = $.fn.zTree.init($("#menuTree"), setting, r);
				var node = ztree.getNodeByParam("id", vm.emotion.dimensionId);
				ztree.selectNode(node);
				vm.emotion.dimensionName = node.name;
			})
		},
		menuTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择菜单",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#menuLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					var node = ztree.getSelectedNodes();
					//选择
					vm.emotion.dimensionId = node[0].id;
					vm.emotion.dimensionName = node[0].name;
					layer.close(index);
				}
			});
		},
		menuTree2 : function() {
			vm.getMenu();
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择菜单",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#menuLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					var node = ztree.getSelectedNodes();
					//选择
					vm.q.dimensionId = node[0].id;
					vm.q.dimensionName = node[0].name;
					layer.close(index);
				}
			});
		},
		validator : function() {
			if (vm.emotion.dimensionId==0) {
				alert("维度不能为空");
				return true;
			}
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData: vm.q,
                page:page
            }).trigger("reloadGrid");
		}
	}
});