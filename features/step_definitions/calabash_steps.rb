require 'calabash-android/calabash_steps'

When(/^I touch the "(.*?)" button$/) do |text|
  touch ("button text:'#{text}'")
end
