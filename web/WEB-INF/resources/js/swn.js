/**
 * @description 滚动新闻
 *
 * @param {id} 指定为滚动新闻的DOM id
 * @param {data} 用于呈现的数据存放的数组
 * @param {length} 滚动长度，默认为5
 * @author 郑宇卫
 */
function SWN(id, data, length) {
    var waper = $('#' + id);

    // 滚动速度
    this.scrollSpeed = 2000;

    // 用于停止滚动
    var intervalId = undefined;

    // 设置默认的列表长度
    if (length == undefined) length = 7;

    // 目前只支持5個長度的列表
    if (data != undefined && data.length < length) return;

    /**
     * @description 初始化新闻滚动
     */
    var init = function () {
        var htmlStr = "<ul>";
        if (data != undefined) {
            for (var i = 0; i < length; i++) {
                htmlStr += '<li><span class="wnew-time">';
                htmlStr += Highcharts.dateFormat('%m-%d %M:%S', new Date()) + '</span><span class="wnew-con"> ';
                htmlStr += data[i] + '</span></li>';
            }
        } else {
            for (var i = 0; i < length; i++) {
                htmlStr += '<li><span class="wnew-time"></span><span class="wnew-con"></span></li>';
            }
        }

        htmlStr += "</ul>";
        // 设置到目标中
        waper.html(htmlStr);

    };

    // 初始化代码
    init();

    /**
     * @description 往滚动新闻中添加一条新闻
     */
    this.addNewLine = function (html) {
        var lastLi = waper.find('ul li:last');
        var height = lastLi.height();
        // 删除最后一个元素
        lastLi.fadeOut(400, function () {
            lastLi.remove();
        });
        // 增加一项
        waper.find('ul li:first').before('<li style="height: 0"><span class="wnew-time">'
            + Highcharts.dateFormat('%m-%d %M:%S', new Date())
            + '</span><span class="wnew-con"> ' + html + '</span></li>');

        waper.find('ul li:first').css({'opacity': 0});
        waper.find('ul li:first').animate({'height': height, 'opacity': 1}, 1000);
    };

    /**
     * @description 开始滚动
     */
    this.start = function () {
        // 如果没有初始数据，则等待更新
        if (data == undefined) return;
        var self = this;

        function scrollWordNews() {
            var newHtml = waper.find('ul li:last .wnew-con').html();
            self.addNewLine(newHtml);
        };
        intervalId = window.setInterval(scrollWordNews, this.scrollSpeed);
    };

    /**
     * @description 停止滚动
     */
    this.stop = function () {
        window.clearInterval(intervalId);
    }
};