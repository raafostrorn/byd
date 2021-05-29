//请求前缀
var baseURL = "/";

// 登录token
var token = localStorage.getItem("token");
if (token == 'null') {
	parent.location.href = baseURL + 'login.html';
}

// jquery全局配置
$.ajaxSetup({
	dataType : "json",
	cache : false,
	headers : {
		"token" : token
	},
	xhrFields : {
		withCredentials : true
	},
	complete : function(xhr) {
		// token过期，则跳转到登录页面
		if (xhr.responseJSON.code == 401) {
			parent.location.href = baseURL + 'login.html';
		}
	}
});

// 判断是否为空
function isBlank(value) {
	return !value || !/\S/.test(value)
}

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		dimension : {
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
		this.drawShengliangLine();
		this.drawQingganPie();
		this.initDateRang();
	},
	watch : {
		selected1 : function(parentId, oldVal) {
			this.selected2 = '全部';
			this.selected3 = '全部';
			this.selected4 = '全部';
			this.dim2 = [];
			this.dim3 = [];
			this.dim4 = [];
			if (parentId == '全部') {
				vm.selectId = 0;
			} else {
				$.get(baseURL + "dimension/listfilter/" + parentId, function(r) {
					if (r) {
						vm.dim2 = r
						vm.selectId = parentId;
					}
				})
			}

		},
		selected2 : function(parentId, oldVal) {
			this.selected3 = '全部';
			this.selected4 = '全部';
			this.dim3 = [];
			this.dim4 = [];
			if (parentId == '全部') {
				// TODO
			} else {
				$.get(baseURL + "dimension/listfilter/" + parentId, function(r) {
					if (r) {
						vm.dim3 = r
						vm.selectId = parentId;
					}
				})
			}

		},
		selected3 : function(parentId, oldVal) {
			this.selected4 = '全部';
			this.dim4 = [];
			if (parentId == '全部') {

			} else {
				$.get(baseURL + "dimension/listfilter/" + parentId, function(r) {
					if (r) {
						vm.dim4 = r
						vm.selectId = parentId;
					}
				})

			}

		},
		selected4 : function(parentId, oldVal) {
			if (parentId == '全部') {
				this.selected4 = '全部';
			} else {
				vm.selectId = parentId;
			}

		}

	},
	methods : {
		getDimData : function(parentId) {
			// 加载菜单树
			$.get(baseURL + "dimension/listfilter/" + parentId, function(r) {
				vm.dim1 = r
			})
		},
		initDateRang : function() {
			$('#daterange-btn').daterangepicker({
				maxDate : moment(), // 最大时间
				dateLimit : {
					days : 60
				}, // 起止时间的最大间隔
				// showDropdowns : true,
				// showWeekNumbers : false, //是否显示第几周
				// timePicker : true, //是否显示小时和分钟
				// timePickerIncrement : 60, //时间的增量，单位为分钟
				// timePicker12Hour : false,
				ranges : {
					// 'Today': [moment(), moment()],
					// 'Yesterday': [moment().subtract(1, 'days'),
					// moment().subtract(1, 'days')],
					'15天' : [ moment().subtract(14, 'days'), moment() ],
					'45天' : [ moment().subtract(44, 'days'), moment() ],
					'60天' : [ moment().subtract(59, 'days'), moment() ],
				// 'This Month': [moment().startOf('month'),
				// moment().endOf('month')],
				// 'Last Month': [moment().subtract(1,
				// 'month').startOf('month'), moment().subtract(1,
				// 'month').endOf('month')]
				},
				startDate : moment().subtract(29, 'days'),
				endDate : moment(),
				opens : 'right',
				format : 'YYYY-MM-DD', // 控件中from和to 显示的日期格式
				separator : ' to ',
				locale : {
					applyLabel : '确定',
					cancelLabel : '取消',
					fromLabel : '起始时间',
					toLabel : '结束时间',
					customRangeLabel : '选择时间',
					daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
					monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
					firstDay : 1
				}
			},

			function(start, end) {
				$('#daterange-btn span').html(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
			});
		},
		drawShengliangLine : function() {
			var chart = echarts.init(document.getElementById('ShengliangLine'));
			var option = {
				legend : {
					data : [ '声量走势' ]
				},
				tooltip : {
					trigger : 'axis'
				},
				xAxis : {
					type : 'category',
					boundaryGap : false,
					data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
				},
				yAxis : {
					type : 'value',
					axisLabel : {
						formatter : '{value}'
					}
				},
				series : [ {
					name : '声量走势',
					type : 'line',
					data : [ 1, 5, 15, 13, 12, 13, 10 ],
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					}
				} ]
			};
			chart.setOption(option);
		},
		drawQingganPie : function() {
			var chart = echarts.init(document.getElementById('QingganPie'));
			var option = {
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : [ '直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎' ]
				},
				series : [ {
					name : '访问来源',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : 335,
						name : '直接访问'
					}, {
						value : 310,
						name : '邮件营销'
					}, {
						value : 234,
						name : '联盟广告'
					}, {
						value : 135,
						name : '视频广告'
					}, {
						value : 1548,
						name : '搜索引擎'
					} ],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};
			chart.setOption(option);

		}
	}
});
