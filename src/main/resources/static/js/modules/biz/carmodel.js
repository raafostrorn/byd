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

var Menu = {
	id : "menuTable",
	table : null,
	layerIndex : -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function() {
	var columns = [ {
		field : 'selectItem',
		radio : true
	}, {
		title : '车型',
		field : 'name',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '300px'
	}, {
		title : '别名',
		field : 'nickname',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '300px'
	}, ]
	return columns;
};

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		carmodel : {
			parentId : 0,
		},
		selectId : 0,
		dim1 : [],
		selected1 : '全部',
		dim2 : [],
		selected2 : '全部',
		dim3 : [],
		selected3 : '全部',
		dim4 : [],
		selected4 : '全部',
	},
	created : function() {
		this.getDimData(0);
	},
	mounted : function() {
		this.treeGrid(this.selectId);
	},
	watch : {
		selected1 : function(parentId, oldVal) {
			this.selected2 = '全部';
			this.selected3 = '全部';
			this.selected4 = '全部';
			this.dim2=[];
			this.dim3=[];
			this.dim4=[];
			if (parentId == '全部') {
				vm.selectId=0;
//				vm.clearTreeGrid();
//				vm.treeGrid(vm.selectId);
			} else {
				$.get(baseURL + "carmodel/listfilter/" + parentId, function(r) {
					if (r) {
						vm.dim2 = r
						vm.selectId = parentId;
//						vm.clearTreeGrid();
//						vm.treeGrid();
					}
				})
			}

		},
		selected2 : function(parentId, oldVal) {
			this.selected3 = '全部';
			this.selected4 = '全部';
			this.dim3=[];
			this.dim4=[];
			if (parentId == '全部') {
				//TODO
			} else {
				$.get(baseURL + "carmodel/listfilter/" + parentId, function(r) {
					if (r) {
						vm.dim3 = r
						vm.selectId = parentId;
//						vm.clearTreeGrid();
//						vm.treeGrid();
					}
				})
			}

		},
		selected3 : function(parentId, oldVal) {
			this.selected4 = '全部';
			this.dim4=[];
			if (parentId == '全部') {
				
			} else {
				$.get(baseURL + "carmodel/listfilter/" + parentId, function(r) {
					if (r) {
						vm.dim4 = r
						vm.selectId = parentId;
//						vm.clearTreeGrid();
//						vm.treeGrid();
					}
				})

			}

		},
		selected4 : function(parentId, oldVal) {
			if (parentId == '全部') {
				this.selected4 = '全部';
			} else {
				vm.selectId = parentId;
				vm.clearTreeGrid();
				vm.treeGrid();
			}

		}
	},
	methods : {
		treeGrid : function() {
			var colunms = Menu.initColumn();
			var table = new TreeTable(Menu.id, baseURL + "carmodel/list/0" + this.selectId, colunms);
			//var table = new TreeTable(Menu.id, baseURL + "carmodel/list", colunms);
			table.setExpandColumn(0);
			//table.setRootCodeValue(root);
			table.setIdField("id");
			table.setCodeField("id");
			table.setParentCodeField("parentId");
			table.setExpandAll(true);
			table.init();
			Menu.table = table;
		},
		clearTreeGrid:function(){
			$("div").removeClass("fixed-table-toolbar");
			$("div").removeClass("fixed-table-container");
			vm.treeGrid();
		},
		getDimData : function(parentId) {
			//加载菜单树
			$.get(baseURL + "carmodel/listfilter/" + parentId, function(r) {
				vm.dim1 = r
			})
		},
		downexcel:function(){
			location.href = baseURL + "carmodel/export?selectId=" + vm.selectId+"&token=" + token;
		},
		getMenu : function() {
			//加载菜单树
			$.get(baseURL + "carmodel/list/0", function(r) {
				//添加顶级菜单
				r.push({
					id : 0,
					name : "主维度",
					parentId : -1,
					open : true
				});
				ztree = $.fn.zTree.init($("#menuTree"), setting, r);
				var node = ztree.getNodeByParam("id", vm.carmodel.parentId);
				ztree.selectNode(node);
				vm.carmodel.parentName = node.name;
			})
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.carmodel = {
				parentId : 0
			};
			vm.getMenu();
		},
		update : function(event) {
			var id = vm.getId();
			if (id == null) {
				return;
			}

			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(id)
		},
		saveOrUpdate : function(event) {
			if (vm.validator()) {
				return;
			}
			var url = vm.carmodel.id == null ? "carmodel/save" : "carmodel/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.carmodel),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功.', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del : function(event) {
			var id = vm.getId();
			if (id == null) {
				return;
			}

			confirm('确定要删除选中的维度词库？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "carmodel/delete",
					contentType : "application/json",
					data : JSON.stringify(id),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功.', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo : function(id) {
			$.get(baseURL + "carmodel/info/" + id, function(r) {
				vm.carmodel = r.carmodel;
				vm.getMenu();
			});
		},
		getId : function() {
			var selected = $('#menuTable').bootstrapTreeTable('getSelections');
			if (selected.length == 0) {
				alert("请选择一条记录");
				return null;
			} else {
				return selected[0].id;
			}
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
					//选择上级菜单
					vm.carmodel.parentId = node[0].id;
					vm.carmodel.parentName = node[0].name;
					layer.close(index);
				}
			});
		},
		validator : function() {
			if (isBlank(vm.carmodel.name)) {
				alert("维度名称不能为空");
				return true;
			}
		},
		reload : function(event) {
			vm.showList = true;
			Menu.table.setUrl=baseURL + "carmodel/list/" + this.selectId
			Menu.table.refresh();
		},
		
	}
});
