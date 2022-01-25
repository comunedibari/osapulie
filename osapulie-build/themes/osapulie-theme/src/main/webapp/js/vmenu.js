/**
 * vMenu - Vertical submenu
 * (C) 2011 Sawanna Team (http://sawanna.org)
 */

$.fn.vmenu=function(settings)
{
	vMenu={
		init: function(elem) {
			$(elem).find('li').each(function(){
				var u=$(this).children('ul');
				if (u.length>0) {
					$(this).addClass('has_child');
				}
				var a=$(this).children('a');
				if (a.hasClass('active')) {
					$(this).addClass('active').parents('li').addClass('open');
				}
			});
			
			$(elem).find('ul').each(function(){
				var o=$(this).find('li.open');
				var a=$(this).find('a.active');
				if (o.length == 0 && a.length==0) {
					$(this).css('display','none');
				}
			});
			
			$(elem).find('a').click(function(){
				return vMenu.click($(this));
			});
		},
		click: function(elem) {
			var l=$(elem).parent('li');
			var u=$(l).children('ul');
			
			if (u.length == 0) {
				return this.forward(elem);
			}

			if ($(l).hasClass('open')) {
				$(l).removeClass('open');
				$(l).find('ul').stop(true,true).slideUp(300);
				$(l).find('li').removeClass('open');
			} else {
				$(l).addClass('open');
				$(u).stop(true,true).slideDown(300);
			}
			
			return false;
		},
		forward: function(elem) {
			return true;
		}
	}
	
	vMenu.init($(this));
}
