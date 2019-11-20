    $(function(){
//饼状图		
        var option_ydzb = {
        		tooltip : { //提示框组件
                    trigger: 'item', //触发类型(饼状图片就是用这个)
                    formatter: "{a} <br/>{b} : {d}%" //提示框浮层内容格式器
                },
                /* color:['#ba7ffb','#fd87ab','#11abff','#ffdf33','#ff4e50'], */
                legend: {
                    orient : 'horizontal',  //布局  纵向布局 图例标记居文字的左边 vertical则反之
                    width:40,      //图行例组件的宽度,默认自适应
                    x : 'right',   //图例显示在右边
                    y: 'center',   //图例在垂直方向上面显示居中
                    itemWidth:23,  //图例标记的图形宽度
                    itemHeight:23, //图例标记的图形高度
                    data:['10万以下','10万-50万','50万-100万','100万及以上'],
                    textStyle:{    //图例文字的样式
                        color:'black',  //文字颜色
                        fontSize:16    //文字大小
                    }
                },
                series: [{
                	name : '资产区间',
                    type : 'pie',
                	smooth: true,
                    center:['40%','50%'], //设置饼的原心坐标 不设置就会默认在中心的位置
                    radius : ['0', '200px'],  //饼图的半径,第一项是内半径,第二项是外半径,内半径为0就是真的饼,不是环形
                    startAngle : 90, //旋转角度
                    clockWise : false,
                    itemStyle : {  //图形样式normal:{
                        normal : { //normal 是图形在默认状态下的样式；emphasis 是图形在高亮状态下的样式，比如在鼠标悬浮或者图例联动高亮时。
                        	color : function(params){
                                var colors1 = ['#ff7f7f','#f5efef','#ccffff','#ccccff'];
                        		var colors2 = ['#ffaa80','#feada6','#66cccc','#99ccff'];
                         		return new echarts.graphic.LinearGradient(0, 0, 1, 0, [{  offset: 0,
                                    color: colors1[params.dataIndex]
                                }, {
                                    offset: 1,
                                    color: colors2[params.dataIndex]
                                }]);
                        	},
                        	icon:'pie',
    	                    label : {
    	                        show : true,
    	                        position:'outer', 
    		                	formatter : '{d}%',
    		                	textStyle : {
    		                		color: '#ccccff',
    		                		fontSize : '22'
    		                	}
    	                    },
    	                    labelLine : {
    	                        show : true
    	                    }
                        },
                        emphasis : {}
                    },
                    data:[
    					  {value:15.50,name:"10万以下"},
    					  {value:37.00,name:"10万-50万"},
    					  {value:14.35,name:"50万-100万"},
                          {value:33.15,name:"100万及以上"}]
                }]
	    };
        var echarts_ydzb = echarts.init(document.getElementById('echarts_ydzb'));
		
		
		
//柱状图
var option_nlzb = {  
           	
tooltip: {                	
	trigger: 'axis'  //  item:触发时没有竖线  axis 触发时有坚线
	},               

	//标题
legend: {                    
	show: true,                    
	x: 'center',                    
	y: 'top',                    
	data: ['出借人数','借款笔数']               
},                
           
calculable: true,               

// X轴显示数据 
xAxis: [{                    	
show: true,                        
type: 'category',    
axisLine: {
		 lineStyle: {
		 color: '#CECECE'
		}
		},                   
data: ['1季度','2季度','3季度','4季度']             
}], 

//Y轴       
yAxis: [{                    	
	show: true,                        
	type: 'value',                        
	splitArea: {show: true} ,
	 axisLine: {
                lineStyle: {
                color: '#CECECE'
                }
            }                 
}],  

                             
//表中数据值的添加
series: [{                    	
name: '出借人数',                        
type: 'bar',                       
//柱状图数值 
 data: [181, 198 , 160 , 120 ],   
//字体控制 
itemStyle: {normal: {
 color:'#1c95a0',
  label: {    		
	 show: true, //开启显示    		
	 position: 'top', //在上方显示    		
extStyle: { //数值样式    		    
	color: 'black',    			
		fontSize: 16,    			
		fontWeight: 600    			
		}    		
	}
 }},

},   
{                    	

name: '借款笔数',                       
type: 'bar',                       
data: [48, 73, 36, 64],     
itemStyle: {
normal: {color:'#bdd747',
label: {    		
	 show: true, //开启显示    		
	 position: 'top', //在上方显示    		
extStyle: { //数值样式    		    
	color: 'black',    			
		fontSize: 16,    			
		fontWeight: 600    			
		}    		
	}          
	}
 },
}]  
          
};
var echarts_nlzb = echarts.init(document.getElementById('echarts_nlzb'));


	      
//曲线图
//标题
var option_zxtt = {
        // 定义样式和数据
        backgroundColor: '#fff',
        tooltip: {
            trigger: 'axis' //触发时有曲线
        },
        legend: {
			show: true,    
			x: 'right',                    
			y: 'top',    
            data: ['2017年', '2018年']
        },

calculable: true,


//底部线位置 X轴上数据 
xAxis: [{
		axisLabel: {
			rotate: 0,
			interval: 0
		},
		//底部分类样式
		axisLine: {
			 lineStyle: {
			 color: '#7c592f',
			}
		},
		type: 'category',
		boundaryGap: false,
		data: ['借款人数','出借人数','出借金额','借款金额','逾期笔数','逾期金额']                
  }],

//右侧数据 Y轴数据 
yAxis: [{
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: '#7c592f'
                }
            }

}],
	//出借人样式控制 
        series: [{
            name: '2017年',
            type: 'line',
            symbol: 'none',
            smooth: 0.3,
            color: ['#993a00'],
		   data: [200, 400, 4300, 4100, 200, 450],

        }, 
	//借款人样式数据 
		{
            name: '2018年',
            type: 'line',
            symbol: 'none',
            smooth: 0.3,
            color: ['#ff9516'],
            data: [120, 320,6300, 6100, 600, 800],   

        }]

    };
	//出借人 ID 
    var echarts_zxtt = echarts.init(document.getElementById('echarts_zxtt'));
		

		


        var btn_index=0;
        /*右边按钮点击*/
        $('.section-btn li').each(function(index) {
            $(this).click(function(){
                btn_index=index;
                scroller();
            })
        });
        /*翻页按钮点击*/
        $('.go-btn').one('click',btn_go);
        function btn_go(){
            go_up();scroller();    
            setTimeout(function(){$('.go-btn').one('click',btn_go)},1000)
        };
        /*响应鼠标*/
        $('.section-wrap').one('mousewheel',mouse_);
        function mouse_(event){
            if (event.deltaY<0) {go_up()}
            else{go_down()}
            scroller();
            setTimeout(function(){$('.section-wrap').one('mousewheel',mouse_)},1000)
        };
        
        /*当前页面赋值*/
        function go_up(){btn_index++;if(btn_index==$('.section-btn li').length){btn_index=$('.section-btn li').length-1};}
        function go_down(){btn_index--;if(btn_index<0){btn_index=0};}
        function page_scale(btn_index){
        	var section_content = $(".section-wrap").find('.section').eq(btn_index).find('.section_content');
         	var this_width = section_content.css('width');
     	    var this_height = section_content.css('height');
         	this_height = parseInt(this_height);
         	this_width = parseInt(this_width);
         	var window_height = window.innerHeight;
         	var window_width = window.innerWidth;

        }

        function scroller(){
            $('.section-btn li').eq(btn_index).addClass('cur').siblings().removeClass('cur');    
            $('.section-wrap').attr("class","section-wrap").addClass(function() {
				//alert(btn_index)
                    return "put-section-"+btn_index;
             }).find('.section').eq(btn_index).find('.title').addClass('active');
            page_scale(btn_index);
            $(".section-wrap").find('.section').eq(btn_index).find('.count_up_num').each(function(){
            	var cnum = $(this).attr("cnum");
            	if(cnum == null){
            	    $(this).countUp();
            	    $(this).attr("cnum","0");
            	}
            });
         
		 if(btn_index == 2){
	            setTimeout(function(){
	            echarts_ydzb.setOption(option_ydzb);
   	            }, 700);
            }
		 if(btn_index == 4){
       	     setTimeout(function(){
			 echarts_nlzb.setOption(option_nlzb);
      	   }, 700);
           }
		  
		    if(btn_index == 3){
       	     setTimeout(function(){
			 echarts_zxtt.setOption(option_zxtt);
      	   }, 700);
           }

        };
        
        /*响应键盘上下键*/
        $(document).one('keydown',keyaction);
        function keyaction(event){
            var e=event||window.event;
            var key=e.keyCode||e.which||e.charCode;
            switch(key){
                case 38: go_down();scroller();    
                break;
                case 40: go_up();scroller();    
                break;
            };
            setTimeout(function(){$(document).one('keydown',keyaction)},1000)
        }



    });