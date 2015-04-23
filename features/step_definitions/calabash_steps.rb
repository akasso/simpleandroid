require 'calabash-android/calabash_steps'

When(/^I touch the "(.*?)" button$/) do |text|
  touch ("button text:'#{text}'")
end

Given(/^I should see a "(.*?)" button$/) do |text|
  expect(query("button text:'#{text}'")[0]["visible"]).to be true
end

Given(/^I see the "(.*?)"$/) do |text|
  expect(query("textview text:'#{text}'")[0]["visible"]).to be true
end
